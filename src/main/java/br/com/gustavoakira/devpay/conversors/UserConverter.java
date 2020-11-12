package br.com.gustavoakira.devpay.conversors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import br.com.gustavoakira.devpay.dto.UserDTO;
import br.com.gustavoakira.devpay.model.Users;

@Component
public class UserConverter extends BaseConverter<Users,UserDTO>{

	@Override
	public UserDTO convertEntityForDTO(Users entity) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Users, UserDTO>() {
			@Override
			protected void configure() {
			}

		});
		return modelMapper.map(entity, UserDTO.class);
	}

	@Override
	public Users convertDTOtoEntity(UserDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<UserDTO, Users>() {
			@Override
			protected void configure() {
			}

		});
		return modelMapper.map(dto, Users.class);
	}

}
