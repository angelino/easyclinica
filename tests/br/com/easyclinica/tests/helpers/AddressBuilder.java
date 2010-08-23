package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.types.Address;
import br.com.easyclinica.domain.types.City;
import br.com.easyclinica.domain.types.Neighborhood;
import br.com.easyclinica.domain.types.PostalCode;
import br.com.easyclinica.domain.types.State;
import br.com.easyclinica.domain.types.Street;

public class AddressBuilder {

	private Address address;
	
	public AddressBuilder() {
		address = new Address(
				new Street("street"), 
				new Neighborhood("neighborhood"), 
				new PostalCode("123"), 
				new City("city"), 
				new State("SP"));
	}
	public Address instance() {
		return address;
	}
	public AddressBuilder withStreet(String street) {
		address = new Address(
				new Street(street), 
				address.getNeighborhood(), 
				address.getPostalCode(), 
				address.getCity(), 
				address.getState());
		
		return this;
	}
}
