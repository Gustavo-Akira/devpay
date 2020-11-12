package br.com.gustavoakira.devpay.enums;

public enum CreditCardBrand {
	VISA("Visa"),
	MASTERCARD("Master Card"),
	ELO("Elo");
	
	private String description;
	
	CreditCardBrand(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
