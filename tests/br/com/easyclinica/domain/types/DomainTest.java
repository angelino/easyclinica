package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DomainTest {
	@Test
	public void shouldStoreADomain() {
		Domain domain = new Domain("easyclinica");
		
		assertEquals("easyclinica", domain.getDomain());
	}
	
	@Test
	public void shouldCompare() {
		Domain one = new Domain("easyclinica");
		Domain two = new Domain("easyclinica");
		Domain three = new Domain("clinicadavila");
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
