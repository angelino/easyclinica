package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.types.Address;

public class AddressBuilder {

	private Address address;
	
	public AddressBuilder() {
		address = new Address();
		
		address.setStreet("street");
		address.setNeighborhood("neighborhood");
		address.setPostalCode("123");
		address.setCity("city");
		address.setState("SP");
	}
	public Address instance() {
		return address;
	}
	public AddressBuilder withStreet(String street) {
		address.setStreet(street);
		
		return this;
	}
	
	public AddressBuilder withNeighborhood(String neighborhood) {
		address.setNeighborhood(neighborhood);
		
		return this;
	}
	
	public AddressBuilder withPostalCode(String postalCode) {
		address.setPostalCode(postalCode);
		
		return this;
	}
	
	public AddressBuilder withCity(String city) {
		address.setCity(city);
		
		return this;
	}
	
	public AddressBuilder withState(String state) {
		address.setState(state);
		
		return this;
	}
}
