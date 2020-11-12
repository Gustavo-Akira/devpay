package br.com.gustavoakira.devpay.service;

import java.util.List;

import br.com.gustavoakira.devpay.dto.UserDTO;
import br.com.gustavoakira.devpay.model.Transaction;
import br.com.gustavoakira.devpay.model.Users;

public interface UsersService {
	Users searchEntity(String login);
	UserDTO search(String login);
	void updateAmount(Transaction savedTransaction, Boolean isCreditCard);
	void validate(Users... users);
	UserDTO add(UserDTO dto);
	
	List<UserDTO> list(String login);
}
