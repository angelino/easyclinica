package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WebsiteTests {
	@Test
	public void shouldStoreAWebsite() {
		Website website = new Website("http://somesite.com");
		
		assertEquals("http://somesite.com", website.get());
	}
}
