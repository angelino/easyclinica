package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EmailTests {
	@Test
	public void shouldStoreAnEmail() {
		Email email = new Email("some@person.com");
		
		assertEquals("some@person.com", email.getEmail());
	}
	
	@Test
	public void shouldCompare() {
		Email one = new Email("me@me.com");
		Email two = new Email("me@me.com");
		Email three = new Email("you@you.com");
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
