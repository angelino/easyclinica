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
		
		assertEquals("street", address.getStreet().get());
		assertEquals("Vila ABC", address.getNeighborhood().get());
		assertEquals("123", address.getPostalCode().get());
		assertEquals("Sao Paulo", address.getCity().get());
		assertEquals("SP", address.getState().get());
	}
}
