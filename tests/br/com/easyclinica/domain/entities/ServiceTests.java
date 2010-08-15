package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Name;

public class ServiceTests {
	@Test
	public void shouldContainNameAndCH() {
		ServicesTable table = new ServicesTable(new Name("table"));
		Service m = new Service(table, new Name("service"), new CH(10));
		
		assertEquals(new Name("service"), m.getName());
		assertEquals(new CH(10), m.getCh());
	}
}
