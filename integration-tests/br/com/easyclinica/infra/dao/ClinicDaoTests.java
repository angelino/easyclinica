package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClinicDaoTests extends BaseIntegrationTests {

	@Test
	public void shouldGetClinic() {
		assertEquals(clinic.getName(), new ClinicDao(session).get().getName());
	}
	
	@Test
	public void shouldUpdate() {
		clinic.setName("new name");
		
		ClinicDao dao = new ClinicDao(session);
		dao.update(clinic);
		
		assertEquals("new name", dao.get().getName());
	}
}
