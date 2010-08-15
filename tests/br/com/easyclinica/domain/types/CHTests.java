package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CHTests {
	@Test
	public void shouldStoreACH() {
		CH ch = new CH(12.34);
		
		assertEquals(12.34, ch.getCh(), 0.001);
	}
	
	@Test
	public void shouldCompare() {
		CH one = new CH(12.34);
		CH two = new CH(12.34);
		CH three = new CH(12.35);
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
