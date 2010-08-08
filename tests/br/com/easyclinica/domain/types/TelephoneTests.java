package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TelephoneTests {
	@Test
	public void shouldStoreATelephone() {
		Telephone phone = new Telephone("123");
		
		assertEquals("123", phone.getTelephone());
	}
}
