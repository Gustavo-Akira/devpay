package br.com.gustavoakira.devpay.conversors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.gustavoakira.devpay.dto.TransactionDTO;
import br.com.gustavoakira.devpay.model.Transaction;
import br.com.gustavoakira.devpay.service.UsersService;

@Component
public class TransactionConverter extends BaseConverter<Transaction, TransactionDTO>{
	
	@Autowired
	private UsersService service;
	
	@Override
	public TransactionDTO convertEntityForDTO(Transaction entity) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Transaction, TransactionDTO>() {
			@Override
			protected void configure() {
			}

		});
		return modelMapper.map(entity, TransactionDTO.class);
	}
	
	@SuppressWarnings("unchecked")
	public Page<TransactionDTO> convertPageEntitytoDTO(Page<Transaction> entity){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Page<Transaction>, Page<TransactionDTO>>() {
			@Override
			protected void configure() {
			}
		});
		return modelMapper.map(entity, Page.class);
	}

	@Override
	public Transaction convertDTOtoEntity(TransactionDTO dto) {
		return Transaction.builder()
				.code(dto.getCode())
				.dateHour(dto.getDateHour())
				.amount(dto.getAmount())
				.userTo(service.searchEntity(dto.getUserTo().getLogin()))
				.userFrom(service.searchEntity(dto.getUserFrom().getLogin()))
				.build();
	}

}
