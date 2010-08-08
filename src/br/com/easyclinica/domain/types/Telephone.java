package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Telephone {
	private String telephone;
	
	protected Telephone() {}
	public Telephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public String toString() {
		return getTelephone();
	}
	public static Telephone empty() {
		return new Telephone("");
	}
}
