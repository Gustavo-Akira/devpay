package br.com.gustavoakira.devpay.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gustavoakira.devpay.dto.LoginDTO;
import br.com.gustavoakira.devpay.dto.TokenDTO;
import br.com.gustavoakira.devpay.service.impl.TokenService;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController extends BaseController<TokenDTO>{
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService service;
	
	@PostMapping
	public ResponseEntity<TokenDTO> auntheticate(LoginDTO login){
		UsernamePasswordAuthenticationToken dataLogin = login.convert();
		try {
			Authentication authenticate = manager.authenticate(dataLogin);
			String token = service.generateToken(authenticate);
			return successWithItemResponse(new TokenDTO(token,"Bearer"));
		}catch(AuthenticationException e) {
			return badRequestResponse();
		}
	}
}
