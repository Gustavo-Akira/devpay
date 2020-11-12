package br.com.gustavoakira.devpay.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.gustavoakira.devpay.enums.CreditCardBrand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "creditcards")
public class CreditCard extends BaseEntity{
	@Column(name="number",nullable = false)
	private String number;
	
	@Column(name = "token")
	private String numberToken;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "brand",nullable = false)
	private CreditCardBrand brand;
	
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="user_id",nullable = false)
	private Users user;
}
