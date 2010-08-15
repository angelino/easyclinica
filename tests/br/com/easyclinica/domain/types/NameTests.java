package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NameTests {
	@Test
	public void shouldStoreAName() {
		Name name = new Name("John Doe");
		
		assertEquals("John Doe", name.getName());
	}
	
	@Test
	public void shouldCompare() {
		Name one = new Name("John Doe");
		Name two = new Name("John Doe");
		Name three = new Name("Jane Doe");
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
