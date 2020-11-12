package br.com.gustavoakira.devpay.util;

public class CreditCardUtil {
	public static String mask(String numeroCartao) {
		return numeroCartao.replaceAll("\\b(\\d{4})(\\d{8})(\\d{4})", "$1XXXXXXXX$3");
	}
}
