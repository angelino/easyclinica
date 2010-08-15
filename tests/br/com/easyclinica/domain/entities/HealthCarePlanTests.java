package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.easyclinica.tests.helpers.AddressBuilder;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;

public class HealthCarePlanTests {

	@Test
	public void shouldBeActive() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		
		assertTrue(plan.getActive().isActive());
	}
	
	@Test
	public void shouldHaveCompleteInformation() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		
		assertEquals("Amil", plan.getName().toString());
		assertEquals("123", plan.getTelephone().toString());
		assertEquals("email@email.com", plan.getEmail().toString());
		assertEquals("website.com", plan.getWebsite().toString());
		assertEquals("contact", plan.getContact().toString());
		assertEquals("obs", plan.getObservations().toString());
		
		assertEquals(new AddressBuilder().instance().getStreet().toString(), plan.getAddress().getStreet().toString());
		assertEquals(new AddressBuilder().instance().getCity().toString(), plan.getAddress().getCity().toString());
		assertEquals(new AddressBuilder().instance().getNeighborhood().toString(), plan.getAddress().getNeighborhood().toString());
		assertEquals(new AddressBuilder().instance().getPostalCode().toString(), plan.getAddress().getPostalCode().toString());
		assertEquals(new AddressBuilder().instance().getState().toString(), plan.getAddress().getState().toString());
	}
	
	@Test
	public void shouldBeDeactived() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		
		assertTrue(plan.getActive().isActive());
		
		plan.deactivate();
		
		assertFalse(plan.getActive().isActive());
	}
}
