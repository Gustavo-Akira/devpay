package br.com.gustavoakira.devpay.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.gustavoakira.devpay.dto.TransactionDTO;

public interface TransactionService {
	TransactionDTO proccess(TransactionDTO transactionDTO);
	
	Page<TransactionDTO> list(Pageable pagination, String user);
}
