package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddressTests {

	@Test
	public void shouldHaveFullAddress() {
		Address address = new Address(
				new Street("street"), 
				new Neighborhood("Vila ABC"), 
				new PostalCode("123"), 
				new City("Sao Paulo"), 
				new State("SP"));
		
		assertEquals("street", address.getStreet().getStreet());
		assertEquals("Vila ABC", address.getNeighborhood().getNeighborhood());
		assertEquals("123", address.getPostalCode().getPostalCode());
		assertEquals("Sao Paulo", address.getCity().getCity());
		assertEquals("SP", address.getState().getState());
	}
}
