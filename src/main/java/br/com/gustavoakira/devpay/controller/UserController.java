package br.com.gustavoakira.devpay.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gustavoakira.devpay.controller.swagger.IUserController;
import br.com.gustavoakira.devpay.dto.UserDTO;
import br.com.gustavoakira.devpay.service.UsersService;

@RestController
@RequestMapping("/api/v1")
public class UserController extends BaseController<UserDTO> implements IUserController{
	
	@Autowired
	private UsersService service;
	
	@GetMapping("/user/{login}/amount")
	public ResponseEntity<UserDTO> consultAmount(@PageableDefault(page=0,size=20) Pageable paginacao, @PathVariable String login){
		UserDTO userDTO = service.search(login);
		return successWithItemResponse(userDTO);
	}
	
	@GetMapping("/user/contacts")
	public ResponseEntity<List<UserDTO>> list(@RequestParam String login){
		List<UserDTO> users = service.list(login);
		return listResponse(users);
	}
	
	@GetMapping("/user/{login}")
	public ResponseEntity<UserDTO> consult(@PathVariable String login) {
		UserDTO usuario = service.search(login);
		return successWithItemResponse(usuario);
	}

	@Override
	public ResponseEntity<UserDTO> add(@RequestBody @Valid UserDTO dto) {
		return createdResponse(service.add(dto));
	}

}
