package br.com.easyclinica.domain.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.easyclinica.domain.types.Email;
import br.com.easyclinica.domain.types.HealthCareId;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Observations;
import br.com.easyclinica.domain.types.Telephone;
import br.com.easyclinica.tests.helpers.AddressBuilder;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;

public class PatientTests {

	@Test
	public void shouldHaveCompleteInformation() {
		Patient sickGuy = new Patient(
				1,
				new Name("John Doe"),
				new AddressBuilder().instance(),
				new Telephone("1234"),
				new Telephone("4567"),
				new Email("patient@sick.com"),
				new HealthCarePlanBuilder().instance(),
				new HealthCareId("123"),
				new Observations("some obs")
				); 
		
		assertNotNull(sickGuy);
		assertEquals(1, sickGuy.getId());
		assertEquals(new Name("John Doe"), sickGuy.getName());
		assertEquals(new AddressBuilder().instance(), sickGuy.getAddress());
		assertEquals(new Telephone("1234"), sickGuy.getTelephone());
		assertEquals(new Telephone("4567"), sickGuy.getCellphone());
		assertEquals(new Email("patient@sick.com"), sickGuy.getEmail());
		assertEquals(new HealthCarePlanBuilder().instance().getId(), sickGuy.getHealthCarePlan().getId());
		assertEquals(new HealthCareId("123"), sickGuy.getHealthCareId());
		assertEquals(new Observations("some obs"), sickGuy.getObservations());
	}
}
