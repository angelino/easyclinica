package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CHTests {
	@Test
	public void shouldStoreACH() {
		CH ch = new CH(12.34);
		
		assertEquals(12.34, ch.getCh(), 0.001);
	}
}
