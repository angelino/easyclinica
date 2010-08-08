package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NeighborhoodTests {
	@Test
	public void shouldStoreANeighborhood() {
		Neighborhood nhood = new Neighborhood("St Barks");
		
		assertEquals("St Barks", nhood.getNeighborhood());
	}
}
