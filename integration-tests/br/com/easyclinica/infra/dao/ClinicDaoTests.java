package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClinicDaoTests extends BaseIntegrationTests {

	@Test
	public void shouldGetClinic() {
		assertEquals(clinic.getName(), new ClinicDao(session).get().getName());
	}
}
