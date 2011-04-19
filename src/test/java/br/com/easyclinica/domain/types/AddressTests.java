package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.easyclinica.tests.helpers.AddressBuilder;

public class AddressTests {

	@Test
	public void shouldHaveFullAddress() {
		Address address = new AddressBuilder()
								.withStreet("street")
								.withNeighborhood("Vila ABC")
								.withPostalCode("123")
								.withCity("Sao Paulo")
								.withState("SP")
								.instance();
				
		assertEquals("street", address.getStreet());
		assertEquals("Vila ABC", address.getNeighborhood());
		assertEquals("123", address.getPostalCode());
		assertEquals("Sao Paulo", address.getCity());
		assertEquals("SP", address.getState());
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
