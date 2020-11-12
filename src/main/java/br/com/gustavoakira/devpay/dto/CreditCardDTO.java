package br.com.gustavoakira.devpay.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.gustavoakira.devpay.enums.CreditCardBrand;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CreditCardDTO {
	
	@NotBlank
	private CreditCardBrand brand;
	
	@NotBlank
	private String securityCode;

	@NotBlank
	private String expirationDate;

	@NotBlank
	private String ownerName;
	
	private String number;

	private String tokenNumber;

	@NotNull
	private UserDTO usuario;

	private Boolean isSaved = false;

}
