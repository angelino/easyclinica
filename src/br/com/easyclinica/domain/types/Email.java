package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Email {
	private String email;
	
	protected Email() {}
	public Email(String email) {
		this.email = email;
	}
	
	public String get() {
		return email;
	}
	
	public String toString() {
		return get();
	}
	public static Email empty() {
		return new Email("");
	}
}
