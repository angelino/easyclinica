package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Street {
	private String street;
	
	protected Street() {}
	public Street(String street) {
		this.street = street;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String toString() {
		return getStreet();
	}
}