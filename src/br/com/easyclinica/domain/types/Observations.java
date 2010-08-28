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

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Observations) ) return false;
		
		Observations other = (Observations)obj;
		
		return this.getObservations() == other.getObservations();
	}
	
	@Override
	public int hashCode() {
		return observations.hashCode();
	}

	public static Observations empty() {
		return new Observations("");
	}
}
