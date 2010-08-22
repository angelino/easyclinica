package br.com.easyclinica.domain.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.easyclinica.domain.types.HealthCareId;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Observations;
import br.com.easyclinica.tests.helpers.AddressBuilder;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;

public class PatientTests {

	@Test
	public void shouldHaveCompleteInformation() {
		Patient sickGuy = new Patient(
				new Name("John Doe"),
				new AddressBuilder().instance(),
				new HealthCarePlanBuilder().instance(),
				new HealthCareId("123"),
				new Observations("some obs")
				); 
		
		assertNotNull(sickGuy);
		assertEquals(new Name("John Doe"), sickGuy.getName());
		assertEquals(new AddressBuilder().instance(), sickGuy.getAddress());
		assertEquals(new HealthCarePlanBuilder().instance().getId(), sickGuy.getHealthCarePlan().getId());
		assertEquals(new HealthCareId("123"), sickGuy.getHealthCareId());
		assertEquals(new Observations("some obs"), sickGuy.getObservations());
	}
}
