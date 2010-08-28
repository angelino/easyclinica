package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ObservationsTests {
	@Test
	public void shouldStoreAnObservation() {
		Observations obs = new Observations("some obs");
		
		assertEquals("some obs", obs.getObservations());
	}
	
	@Test
	public void shouldCompare() {
		Observations one = new Observations("obs");
		Observations two = new Observations("obs");
		Observations three = new Observations("obs2");
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
