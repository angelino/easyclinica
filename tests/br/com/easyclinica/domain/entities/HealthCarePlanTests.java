package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.easyclinica.domain.types.Email;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Observations;
import br.com.easyclinica.domain.types.Telephone;
import br.com.easyclinica.domain.types.Website;
import br.com.easyclinica.tests.helpers.AnExampleOf;

public class HealthCarePlanTests {

	@Test
	public void shouldHaveANameAndAServicesTable() {
		HealthCarePlan plan = new HealthCarePlan(new Name("Amil"), new ServicesTable(new Name("table")));
		
		assertEquals("Amil", plan.getName().toString());
		assertNotNull(plan.getTable());
		assertEquals("table", plan.getTable().getName().toString());
	}
	
	@Test
	public void shouldHaveCompleteInformation() {
		HealthCarePlan plan = new HealthCarePlan(
				new Name("Amil"),
				AnExampleOf.address(),
				new Telephone("123"),
				new Email("email@email.com"),
				new Website("website.com"),
				new Name("contact"),
				new Observations("obs"),
				new ServicesTable(new Name("table"))
		);
		
		assertEquals("Amil", plan.getName().toString());
		assertEquals("123", plan.getTelephone().toString());
		assertEquals("email@email.com", plan.getEmail().toString());
		assertEquals("website.com", plan.getWebsite().toString());
		assertEquals("contact", plan.getContact().toString());
		assertEquals("obs", plan.getObservations().toString());
		
		assertEquals(AnExampleOf.address().getStreet().toString(), plan.getAddress().getStreet().toString());
		assertEquals(AnExampleOf.address().getCity().toString(), plan.getAddress().getCity().toString());
		assertEquals(AnExampleOf.address().getNeighborhood().toString(), plan.getAddress().getNeighborhood().toString());
		assertEquals(AnExampleOf.address().getPostalCode().toString(), plan.getAddress().getPostalCode().toString());
		assertEquals(AnExampleOf.address().getState().toString(), plan.getAddress().getState().toString());
	}
}
