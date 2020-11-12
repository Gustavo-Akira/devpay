package br.com.gustavoakira.devpay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gustavoakira.devpay.conversors.TransactionConverter;
import br.com.gustavoakira.devpay.dto.TransactionDTO;
import br.com.gustavoakira.devpay.model.Transaction;
import br.com.gustavoakira.devpay.repository.TransactionRepository;
import br.com.gustavoakira.devpay.service.CreditCardService;
import br.com.gustavoakira.devpay.service.TransactionService;
import br.com.gustavoakira.devpay.service.UsersService;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionConverter converter;
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private CreditCardService service;
	
	@Autowired
	private UsersService uService;
	
	@Override
	public TransactionDTO proccess(TransactionDTO transactionDTO) {
		Transaction savedTransaction = save(transactionDTO);
		service.save(transactionDTO.getCreditCard());
		uService.updateAmount(savedTransaction, transactionDTO.getIsCreditCard());
		return converter.convertEntityForDTO(savedTransaction);
	}
	
	private Transaction save(TransactionDTO dto) {
		Transaction transaction = converter.convertDTOtoEntity(dto);
		uService.validate(transaction.getUserTo(),transaction.getUserFrom());
		return repository.save(transaction);
	}
	@Override
	public Page<TransactionDTO> list(Pageable pagination, String user) {
		Page<Transaction> transactions = repository.findByFrom_LoginOrTo_Login(user, user, pagination);
		return converter.convertPageEntitytoDTO(transactions);
	}

}
