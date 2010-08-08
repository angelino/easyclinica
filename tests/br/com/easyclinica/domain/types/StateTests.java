package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StateTests {
	@Test
	public void shouldStoreAState() {
		State state = new State("SP");
		
		assertEquals("SP", state.get());
	}
}
