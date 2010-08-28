package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WebsiteTests {
	@Test
	public void shouldStoreAWebsite() {
		Website website = new Website("http://somesite.com");
		
		assertEquals("http://somesite.com", website.getWebsite());
	}
	
	@Test
	public void shouldCompare() {
		Website one = new Website("site.com");
		Website two = new Website("site.com");
		Website three = new Website("site2.com");
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
