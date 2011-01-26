package br.com.easyclinica.infra.multitenancy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TenantUrlParserTests {

	private TenantUrlParser parser;
	
	@Before
	public void setUp() {
		parser = new DefaultTenantUrlParser();
	}
	
	@Test
	public void shouldGetTenantInAValidUrl() {
		assertEquals("clinicadavila", parser.parse("http://clinicadavila.easyclinica.com.br"));
		assertEquals("clinicax", parser.parse("http://clinicax.easyclinica.com.br"));
	}
	
	@Test
	public void shouldGetTenantInAUrlWithWWW() {
		assertEquals("clinicadavila", parser.parse("http://www.clinicadavila.easyclinica.com.br"));
		assertEquals("clinicax", parser.parse("http://www.clinicax.easyclinica.com.br"));
	}
}
