package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Neighborhood {
	private String neighborhood;
	
	protected Neighborhood() {}
	public Neighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	public String getNeighborhood() {
		return neighborhood;
	}
	
	public String toString() {
		return getNeighborhood();
	}
}