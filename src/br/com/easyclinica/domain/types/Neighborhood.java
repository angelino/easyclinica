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

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Neighborhood) ) return false;
		
		Neighborhood other = (Neighborhood)obj;
		
		return this.getNeighborhood() == other.getNeighborhood();
	}
	
	@Override
	public int hashCode() {
		return neighborhood.hashCode();
	}

}
