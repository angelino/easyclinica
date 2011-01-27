package br.com.easyclinica.infra.multitenancy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.infra.web.HttpRequestWrapper;
import static org.mockito.Mockito.*;

public class TenantUrlParserTests {

	private TenantUrlParser parser;
	private HttpRequestWrapper request;
	
	@Before
	public void setUp() {
		request = mock(HttpRequestWrapper.class);
		parser = new DefaultTenantUrlParser(request);
	}
	
	@Test
	public void shouldGetTenantInAValidUrl() {
		when(request.getUrl()).thenReturn("http://clinicadavila.easyclinica.com.br");
		assertEquals("clinicadavila", parser.parse());
		
		when(request.getUrl()).thenReturn("http://clinicax.easyclinica.com.br");
		assertEquals("clinicax", parser.parse());
	}
	
	@Test
	public void shouldGetTenantInAUrlWithWWW() {
		when(request.getUrl()).thenReturn("http://www.clinicadavila.easyclinica.com.br");
		assertEquals("clinicadavila", parser.parse());
		
		when(request.getUrl()).thenReturn("http://www.clinicax.easyclinica.com.br");
		assertEquals("clinicax", parser.parse());
	}
}
