package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StateTests {
	@Test
	public void shouldStoreAState() {
		State state = new State("SP");
		
		assertEquals("SP", state.getState());
	}
	
	@Test
	public void shouldCompare() {
		State one = new State("SP");
		State two = new State("SP");
		State three = new State("RJ");
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
