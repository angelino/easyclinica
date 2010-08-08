package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StreetTests {
	@Test
	public void shouldStoreAStreet() {
		Street street = new Street("123 Street");
		
		assertEquals("123 Street", street.get());
	}
}
