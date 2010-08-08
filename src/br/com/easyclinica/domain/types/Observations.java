package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Observations {
	private String observations;
	
	protected Observations() {}
	public Observations(String observations) {
		this.observations = observations;
	}
	
	public String getObservations() {
		return observations;
	}
	
	public String toString() {
		return getObservations();
	}
	public static Observations empty() {
		return new Observations("");
	}
}
