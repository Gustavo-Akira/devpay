package br.com.gustavoakira.devpay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gustavoakira.devpay.conversors.CreditCardConverter;
import br.com.gustavoakira.devpay.dto.CreditCardDTO;
import br.com.gustavoakira.devpay.model.CreditCard;
import br.com.gustavoakira.devpay.repository.CreditCardRepository;
import br.com.gustavoakira.devpay.service.CreditCardService;
import br.com.gustavoakira.devpay.service.UsersService;

@Service
public class CreditCardServiceImpl implements CreditCardService {
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Autowired
	private CreditCardConverter converter;
	
	@Autowired
	private UsersService service;

	@Override
	public CreditCardDTO save(CreditCardDTO creditCardDTO) {
		CreditCardDTO creditCard = null;
		if(creditCardDTO.getIsSaved()){
			CreditCard creditCardEntity = converter.convertDTOtoEntity(creditCardDTO);
			service.validate(creditCardEntity.getUser());
			CreditCard savedCreditCard = creditCardRepository.save(creditCardEntity);
			creditCard = converter.convertEntityForDTO(savedCreditCard);
		}
		return creditCard;
	}
	
	
}
