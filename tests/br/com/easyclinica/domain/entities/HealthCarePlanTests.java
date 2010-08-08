package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.easyclinica.domain.types.Email;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Observations;
import br.com.easyclinica.domain.types.Telephone;
import br.com.easyclinica.domain.types.Website;
import br.com.easyclinica.tests.helpers.AnExampleOf;

public class HealthCarePlanTests {

	@Test
	public void shouldHaveAName() {
		HealthCarePlan plan = new HealthCarePlan(new Name("Amil"));
		
		assertEquals("Amil", plan.getName().get());
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
				new Observations("obs")
		);
		
		assertEquals("Amil", plan.getName().get());
		assertEquals("123", plan.getTelephone().get());
		assertEquals("email@email.com", plan.getEmail().get());
		assertEquals("website.com", plan.getWebsite().get());
		assertEquals("contact", plan.getContact().get());
		assertEquals("obs", plan.getObservations().get());
		
		assertEquals(AnExampleOf.address().getStreet().get(), plan.getAddress().getStreet().get());
		assertEquals(AnExampleOf.address().getCity().get(), plan.getAddress().getCity().get());
		assertEquals(AnExampleOf.address().getNeighborhood().get(), plan.getAddress().getNeighborhood().get());
		assertEquals(AnExampleOf.address().getPostalCode().get(), plan.getAddress().getPostalCode().get());
		assertEquals(AnExampleOf.address().getState().get(), plan.getAddress().getState().get());
	}
}
