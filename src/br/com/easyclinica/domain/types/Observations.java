package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Observations {
	private String observations;
	
	protected Observations() {}
	public Observations(String observations) {
		this.observations = observations;
	}
	
	public String get() {
		return observations;
	}
	
	public String toString() {
		return get();
	}
	public static Observations empty() {
		return new Observations("");
	}
}
