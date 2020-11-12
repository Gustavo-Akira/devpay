package br.com.gustavoakira.devpay.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gustavoakira.devpay.dto.TransactionDTO;
import br.com.gustavoakira.devpay.service.TransactionService;
@RestController
@RequestMapping("/api/v1")
public class TransactionController extends BaseController<TransactionDTO>{
	@Autowired
	private TransactionService service;
	
	@PostMapping("/transaction")
	public ResponseEntity<TransactionDTO> save(@RequestBody @Valid TransactionDTO transactionDTO,
			UriComponentsBuilder uriBuilder){
		TransactionDTO transactionResponse = service.proccess(transactionDTO);
		String path = "/transactions/code";
		return createdWithURI(transactionResponse, uriBuilder, path, transactionResponse.getCode());
	}
	
	@GetMapping("/transactions")
	public ResponseEntity<Page<TransactionDTO>> list(@PageableDefault(page = 0, size=20)Pageable pagination,
			@RequestParam String login){
		Page<TransactionDTO> transactions = service.list(pagination, login);
		return pagedListResponse(transactions);
		
	}
}
