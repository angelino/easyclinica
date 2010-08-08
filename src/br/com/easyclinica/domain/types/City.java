package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class City {
	private String city;
	
	protected City() {}
	public City(String city) {
		this.city = city;
	}
	
	public String get() {
		return city;
	}
	
	public String toString() {
		return get();
	}
}
