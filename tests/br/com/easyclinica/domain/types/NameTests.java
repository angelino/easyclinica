package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NameTests {
	@Test
	public void shouldStoreAName() {
		Name name = new Name("John Doe");
		
		assertEquals("John Doe", name.get());
	}
}
