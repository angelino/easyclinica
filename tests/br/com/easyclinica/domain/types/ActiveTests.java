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
	
	@Test
	public void shouldCompare() {
		Active one = Active.active();
		Active two = Active.active();
		Active three = Active.notActive();
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
