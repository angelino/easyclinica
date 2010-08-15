package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.exceptions.InvalidServiceRuleException;
import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.tests.helpers.AddressBuilder;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.ServiceBuilder;

public class HealthCarePlanTests {

	private ServicesTable table;

	@Before
	public void setUp() {
		table = new ServicesTable(new Name("table"));
	}
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
	
	@Test
	public void shouldHaveServiceRules() throws InvalidServiceRuleException {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		Service someService = new ServiceBuilder(table).withId(1).instance();
		Service someService2 = new ServiceBuilder(table).withId(2).instance();
		
		plan.addServiceRule(someService, new Money(10));
		plan.addServiceRule(someService2, new CH(20));
		
		assertEquals(someService, plan.getServiceRules().get(0).getService());
		assertEquals(someService2, plan.getServiceRules().get(1).getService());
	}
	
	@Test(expected=InvalidServiceRuleException.class)
	public void shouldNotAcceptARepeatedServiceRuleForMoney() throws InvalidServiceRuleException {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		Service someService = new ServiceBuilder(table).instance();
		
		plan.addServiceRule(someService, new Money(10));
		plan.addServiceRule(someService, new Money(20));
	}
	
	@Test(expected=InvalidServiceRuleException.class)
	public void shouldNotAcceptARepeatedServiceRuleForCHs() throws InvalidServiceRuleException {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		Service someService = new ServiceBuilder(table).instance();
		
		plan.addServiceRule(someService, new CH(10));
		plan.addServiceRule(someService, new CH(20));
	}
}
