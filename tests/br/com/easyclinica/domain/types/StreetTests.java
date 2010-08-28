package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StreetTests {
	@Test
	public void shouldStoreAStreet() {
		Street street = new Street("123 Street");
		
		assertEquals("123 Street", street.getStreet());
	}
	
	@Test
	public void shouldCompare() {
		Street one = new Street("Street");
		Street two = new Street("Street");
		Street three = new Street("Street2");
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
