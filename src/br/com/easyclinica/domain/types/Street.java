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
	

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Street) ) return false;
		
		Street other = (Street)obj;
		
		return this.getStreet() == other.getStreet();
	}
	
	@Override
	public int hashCode() {
		return street.hashCode();
	}

}
