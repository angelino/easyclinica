package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CityTests {

	@Test
	public void shouldStoreACity() {
		City city = new City("Sao Paulo");
		
		assertEquals("Sao Paulo", city.get());
	}
}
