package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private Street street;
	private Neighborhood neighborhood;
	private PostalCode postalCode;
	private City city;
	private State state;

	protected Address() {}
	public Address(Street street, Neighborhood neighborhood,
			PostalCode postalCode, City city, State state) {
				this.street = street;
				this.neighborhood = neighborhood;
				this.postalCode = postalCode;
				this.city = city;
				this.state = state;
	}
	
	public Street getStreet() {
		return street;
	}
	public void setStreet(Street street) {
		this.street = street;
	}
	public Neighborhood getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}
	public PostalCode getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(PostalCode postalCode) {
		this.postalCode = postalCode;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Address) ) return false;
		
		Address other = (Address)obj;
		
		return this.getCity().equals(other.getCity()) &&
			getNeighborhood().equals(other.getNeighborhood()) &&
			getState().equals(other.getState()) &&
			getPostalCode().equals(other.getPostalCode()) &&
			getStreet().equals(other.getStreet());
	}
	
	@Override
	public int hashCode() {
		return getCity().hashCode() * getNeighborhood().hashCode() *
			getState().hashCode() * getPostalCode().hashCode() * getStreet().hashCode();
	}
	
	public static Address empty() {
		return new Address(new Street(""), new Neighborhood(""), new PostalCode(""), new City(""), new State(""));
	}

}
