package br.com.gustavoakira.devpay.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.gustavoakira.devpay.conversors.UserConverter;
import br.com.gustavoakira.devpay.dto.UserDTO;
import br.com.gustavoakira.devpay.exceptions.BusinessException;
import br.com.gustavoakira.devpay.model.Transaction;
import br.com.gustavoakira.devpay.model.Users;
import br.com.gustavoakira.devpay.repository.UsersRepository;
import br.com.gustavoakira.devpay.service.UsersService;

@Service
public class UserServiceImpl implements UsersService{
	
	@Autowired
	private UsersRepository repository;
	
	@Autowired
	private UserConverter converter;
	

	@Override
	@Transactional
	public Users searchEntity(String login) {
		return repository.findByLogin(login);
	}

	@Override
	public UserDTO search(String login) {
		Users user = searchEntity(login);
		return converter.convertEntityForDTO(user);
	}

	@Override
	@Async("asyncExecutor")
	@Transactional
	public void updateAmount(Transaction savedTransaction, Boolean isCreditCard) {
		decrementAmount(savedTransaction, isCreditCard);
		incrementAmount(savedTransaction);
	}
	
	private void incrementAmount(Transaction savedTransaction) {
		repository.updateIncreaseAmount(savedTransaction.getUserTo().getLogin(), savedTransaction.getAmount());
	}
	
	private void decrementAmount(Transaction savedTransaction, Boolean isCreditCard) {
		if(!isCreditCard) {
			repository.updateDecreaseAmount(savedTransaction.getUserFrom().getLogin(), savedTransaction.getAmount());
		}
	}

	@Override
	public void validate(Users... users) {
		Arrays.asList(users).stream().forEach(user->{
			if(user == null) {
				throw new BusinessException("Inexistent User");
			}
		});
	}

	@Override
	public List<UserDTO> list(String login) {
		List<Users> users = repository.findAll();
		return converter.convertEntitystoDTOs(users.stream().filter(v->!v.getLogin().equals(login)).collect(Collectors.toList()));
	}

	@Override
	public UserDTO add(UserDTO dto) {
		Users user= converter.convertDTOtoEntity(dto);
		return converter.convertEntityForDTO(repository.save(user));
	}

}
