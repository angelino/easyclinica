package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Telephone {
	private String telephone;
	
	protected Telephone() {}
	public Telephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String get() {
		return telephone;
	}
	
	public String toString() {
		return get();
	}
	public static Telephone empty() {
		return new Telephone("");
	}
}
