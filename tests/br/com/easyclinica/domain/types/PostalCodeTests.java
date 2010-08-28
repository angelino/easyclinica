package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PostalCodeTests {

	@Test
	public void shouldStoreAPostalCode() {
		PostalCode code = new PostalCode("123");
		
		assertEquals("123", code.getPostalCode());
	}
	
	@Test
	public void shouldCompare() {
		PostalCode one = new PostalCode("123");
		PostalCode two = new PostalCode("123");
		PostalCode three = new PostalCode("456");
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
