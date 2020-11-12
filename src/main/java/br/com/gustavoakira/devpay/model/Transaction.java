package br.com.gustavoakira.devpay.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "transactions")
public class Transaction extends BaseEntity{
	@Column(name = "code",nullable = false)
	private String code;
	
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "user_from",nullable = false)
	private Users userFrom;
	
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "user_to",nullable = false)
	private Users userTo;
	
	@Column(name="date_hour",nullable = false)
	private LocalDateTime dateHour;
	
	@Column(name="value",nullable = false)
	private Double amount;
	
	
}
