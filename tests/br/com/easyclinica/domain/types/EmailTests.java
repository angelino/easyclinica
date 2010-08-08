package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EmailTests {
	@Test
	public void shouldStoreAnEmail() {
		Email email = new Email("some@person.com");
		
		assertEquals("some@person.com", email.get());
	}
}
