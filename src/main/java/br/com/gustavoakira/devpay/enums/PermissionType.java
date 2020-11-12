package br.com.gustavoakira.devpay.enums;

public enum PermissionType {
	User("ROLE_USER"),
	ADMIN("ROLE_ADMIN");
	
	private String code;
	
	private PermissionType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
