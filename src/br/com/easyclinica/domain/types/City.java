package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class City {
	private String city;
	
	protected City() {}
	public City(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}
	
	public String toString() {
		return getCity();
	}
	

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof City) ) return false;
		
		City other = (City)obj;
		
		return this.getCity() == other.getCity();
	}
	
	@Override
	public int hashCode() {
		return city.hashCode();
	}

}
