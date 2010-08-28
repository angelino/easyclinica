package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HealthCareIdTests {
	@Test
	public void shouldStoreAName() {
		HealthCareId id = new HealthCareId("123A");
		
		assertEquals("123A", id.getHealthCareId());
	}
	
	@Test
	public void shouldCompare() {
		HealthCareId one = new HealthCareId("123A");
		HealthCareId two = new HealthCareId("123A");
		HealthCareId three = new HealthCareId("123B");
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
