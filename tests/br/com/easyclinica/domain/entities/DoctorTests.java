package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.easyclinica.tests.helpers.DoctorBuilder;


public class DoctorTests {

	@Test
	public void shouldHaveANameAndACRM() {
		Doctor doctor = new DoctorBuilder().instance();
		
		assertEquals("Doutor", doctor.getName().toString());
		assertNotNull(doctor.getCrm());
	}
}
