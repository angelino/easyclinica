package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.easyclinica.tests.helpers.AddressBuilder;

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
	
	@Test
	public void shouldCompare() {
		Address one = new AddressBuilder().instance();
		Address two = new AddressBuilder().instance();
		Address three = new AddressBuilder().withStreet("123").instance();
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
