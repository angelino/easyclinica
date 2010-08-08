package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Email {
	private String email;
	
	protected Email() {}
	public Email(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String toString() {
		return getEmail();
	}
	public static Email empty() {
		return new Email("");
	}
}
