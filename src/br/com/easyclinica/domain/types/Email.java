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
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Email) ) return false;
		
		Email other = (Email)obj;
		
		return this.getEmail() == other.getEmail();
	}
	
	@Override
	public int hashCode() {
		return email.hashCode();
	}

}
