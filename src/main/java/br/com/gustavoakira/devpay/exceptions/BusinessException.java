package br.com.gustavoakira.devpay.exceptions;

public class BusinessException extends RuntimeException{
	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Exception exception) {
		super(message, exception);
	}

}
