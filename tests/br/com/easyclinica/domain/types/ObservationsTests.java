package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ObservationsTests {
	@Test
	public void shouldStoreAnObservation() {
		Observations obs = new Observations("some obs");
		
		assertEquals("some obs", obs.get());
	}
}
