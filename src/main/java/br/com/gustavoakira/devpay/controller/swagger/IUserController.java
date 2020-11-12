package br.com.gustavoakira.devpay.controller.swagger;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.gustavoakira.devpay.dto.ErrorDTO;
import br.com.gustavoakira.devpay.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "/users")
public interface IUserController {
	@ApiOperation(value = "Consultar saldo de um usuário por login", nickname = "consultarSaldo", notes = "", response = UserDTO.class, responseContainer = "object", authorizations = {
			@Authorization(value = "basicAuth") }, tags = { "users" })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Amount consulted with success", response = UserDTO.class, responseContainer = "object"),
			@ApiResponse(code = 400, message = "Data inconsistent", response = ErrorDTO.class, responseContainer = "object"),
			@ApiResponse(code = 401, message = "User Forbidden to see the information"),
			@ApiResponse(code = 404, message = "User not found") })
	@GetMapping("/user/{login}/amount")
	public ResponseEntity<UserDTO> consultAmount(@PageableDefault(page=0,size=20) Pageable paginacao, @PathVariable String login);

	@ApiOperation(value = "Consult contacts of a user by login", nickname = "listarContatos", notes = "", response = UserDTO.class, responseContainer = "object", authorizations = {
			@Authorization(value = "basicAuth") }, tags = { "users", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Contacts find with success", response = UserDTO.class, responseContainer = "object"),
			@ApiResponse(code = 400, message = "Data inconsistent", response = ErrorDTO.class, responseContainer = "object"),
			@ApiResponse(code = 401, message = "User Forbidden to see the information"),
			@ApiResponse(code = 404, message = "Users not found"),
	})
	@GetMapping("/user/contacts")
	public ResponseEntity<List<UserDTO>> list(@RequestParam String login);

	@ApiOperation(value = "Consult user by login", nickname = "consultarUsuarios", notes = "", response = UserDTO.class, responseContainer = "object", authorizations = {
			@Authorization(value = "basicAuth") }, tags = { "users", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User find with success", response = UserDTO.class, responseContainer = "object"),
			@ApiResponse(code = 400, message = "Data inconsistent", response = ErrorDTO.class, responseContainer = "object"),
			@ApiResponse(code = 401, message = "User Forbidden to see the information"),
			@ApiResponse(code = 404, message = "User not found") })
	@GetMapping("/user/{login}")
	public ResponseEntity<UserDTO> consult(@PathVariable String login);
	
	@ApiOperation("Add a new user")
	@PostMapping("/user")
	public ResponseEntity<UserDTO> add(UserDTO userDTO);
}
