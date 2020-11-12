package br.com.gustavoakira.devpay.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class BaseController<T>{
	protected ResponseEntity<T> createdResponse(T object){
		return ResponseEntity.status(HttpStatus.CREATED).body(object);
	}
	
	protected ResponseEntity<T> createdWithURI(T object, UriComponentsBuilder uriBuilder, String path, String code){
		URI uri = uriBuilder.path(path).buildAndExpand(code).toUri();
		return ResponseEntity.created(uri).body(object);
	}
	
	protected ResponseEntity<T> notFoundResponse(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	protected ResponseEntity<T> successResponse(){
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	protected ResponseEntity<T> successWithItemResponse(T object){
		return ResponseEntity.status(HttpStatus.OK).body(object);
	}
	
	protected ResponseEntity<List<T>> emptyListResponse(){
		List<T> empty = new ArrayList<>();
		return ResponseEntity.status(HttpStatus.OK).body(empty);
	}
	
	protected ResponseEntity<List<T>> listResponse(List<T> itens){
		return ResponseEntity.status(HttpStatus.OK).body(itens);
	}
	
	protected ResponseEntity<T> badRequestResponse(){
		return ResponseEntity.badRequest().build();
	}
	
	protected ResponseEntity<Page<T>>  pagedListResponse(Page<T> itens){
		return ResponseEntity.status(HttpStatus.OK).body(itens);
	}
}
