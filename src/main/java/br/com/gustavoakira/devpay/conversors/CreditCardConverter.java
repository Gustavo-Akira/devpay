package br.com.gustavoakira.devpay.conversors;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.gustavoakira.devpay.dto.CreditCardDTO;
import br.com.gustavoakira.devpay.dto.TransactionDTO;
import br.com.gustavoakira.devpay.model.CreditCard;
import br.com.gustavoakira.devpay.model.Transaction;
import br.com.gustavoakira.devpay.service.UsersService;
import br.com.gustavoakira.devpay.util.CreditCardUtil;

@Component
public class CreditCardConverter extends BaseConverter<CreditCard,CreditCardDTO>{
	
	@Autowired
	private UsersService service;
	
	@Override
	public CreditCardDTO convertEntityForDTO(CreditCard entity) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Transaction, TransactionDTO>() {
			@Override
			protected void configure() {
			}
		});
		return modelMapper.map(entity, CreditCardDTO.class);
	}

	@Override
	public CreditCard convertDTOtoEntity(CreditCardDTO dto) {
		return CreditCard.builder()
				.brand(dto.getBrand())
				.number(CreditCardUtil.mask(dto.getNumber()))
				.numberToken(dto.getTokenNumber())
				.user(service.searchEntity(dto.getUsuario().getLogin()))
				.build();
	}

}
