package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CityTests {

	@Test
	public void shouldStoreACity() {
		City city = new City("Sao Paulo");
		
		assertEquals("Sao Paulo", city.getCity());
	}
	
	@Test
	public void shouldCompare() {
		City one = new City("Sao Paulo");
		City two = new City("Sao Paulo");
		City three = new City("Rio de Janeiro");
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
