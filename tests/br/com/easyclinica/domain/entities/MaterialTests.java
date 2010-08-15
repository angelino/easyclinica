package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Name;

public class MaterialTests {

	@Test
	public void shouldContainNameAndCH() {
		Material m = new Material(new Name("material"), new CH(10));
		
		assertEquals(new Name("material"), m.getName());
		assertEquals(new CH(10), m.getCh());
	}
}
