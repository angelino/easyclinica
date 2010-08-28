package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TelephoneTests {
	@Test
	public void shouldStoreATelephone() {
		Telephone phone = new Telephone("123");
		
		assertEquals("123", phone.getTelephone());
	}
	
	@Test
	public void shouldCompare() {
		Telephone one = new Telephone("123");
		Telephone two = new Telephone("123");
		Telephone three = new Telephone("456");
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
