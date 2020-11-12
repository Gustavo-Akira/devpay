package br.com.gustavoakira.devpay.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.gustavoakira.devpay.model.Users;
import br.com.gustavoakira.devpay.repository.UsersRepository;
import br.com.gustavoakira.devpay.service.impl.TokenService;

public class AuthTokenFilter extends OncePerRequestFilter{
	private TokenService tokenService;
	private UsersRepository repository;
	
	public AuthTokenFilter(TokenService tokenService, UsersRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recoverToken(request);
		boolean valid = tokenService.isValidToken(token);
		if(valid) {
			authenticateClient(token);
		}
		filterChain.doFilter(request, response);
	}
	
	private void authenticateClient(String token) {
		Long id = tokenService.getUserId(token);
		Users user =repository.findById(id).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private String recoverToken(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7,token.length());
	}
}
