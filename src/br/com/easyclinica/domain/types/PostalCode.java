package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class PostalCode {
	private String postalCode;
	
	protected PostalCode() {}
	public PostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public String toString() {
		return getPostalCode();
	}
}
