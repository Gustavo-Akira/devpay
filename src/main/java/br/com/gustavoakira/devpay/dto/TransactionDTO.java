package br.com.gustavoakira.devpay.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class TransactionDTO {
	@NotBlank
	private String code;
	
	@NotNull
	private UserDTO userFrom;
	
	@NotNull
	private UserDTO userTo;
	
	@NotNull
	private LocalDateTime dateHour;
	
	@NotNull
	private Double amount;
	
	private CreditCardDTO creditCard;
	
	private Boolean isCreditCard = false;
}
