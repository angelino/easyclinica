package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NeighborhoodTests {
	@Test
	public void shouldStoreANeighborhood() {
		Neighborhood nhood = new Neighborhood("St Barks");
		
		assertEquals("St Barks", nhood.getNeighborhood());
	}
	
	@Test
	public void shouldCompare() {
		Neighborhood one = new Neighborhood("Vila 1");
		Neighborhood two = new Neighborhood("Vila 1");
		Neighborhood three = new Neighborhood("Vila 2");
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}	
}
