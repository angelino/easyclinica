package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PostalCodeTests {

	@Test
	public void shouldStoreAPostalCode() {
		PostalCode code = new PostalCode("123");
		
		assertEquals("123", code.getPostalCode());
	}
}
