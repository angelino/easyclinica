package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ActiveTests {
	@Test
	public void shouldBeActive() {
		Active status = Active.active();
		
		assertTrue(status.isActive());
	}
	
	@Test
	public void shouldBeNotActive() {
		Active status = Active.notActive();
		
		assertFalse(status.isActive());
	}
}
