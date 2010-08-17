package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.exceptions.InvalidMaterialRuleException;
import br.com.easyclinica.domain.exceptions.InvalidServiceRuleException;
import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.MaterialBuilder;
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
	
	@Test
	public void shouldRemoveAServiceRule() throws InvalidServiceRuleException {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		Service someService = new ServiceBuilder(table).instance();
		
		plan.addServiceRule(someService, new CH(10));
		
		assertEquals(1, plan.getServiceRules().size());
		plan.removeServiceRuleById(plan.getServiceRules().get(0).getId());
		assertEquals(0, plan.getServiceRules().size());		
	}
	
	@Test
	public void shouldHaveMaterialRules() throws InvalidMaterialRuleException {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		Material someMaterial = new MaterialBuilder(table).withId(1).instance();
		Material someMaterial2 = new MaterialBuilder(table).withId(2).instance();
		
		plan.addMaterialRule(someMaterial, new Money(10));
		plan.addMaterialRule(someMaterial2, new CH(20));
		
		assertEquals(someMaterial, plan.getMaterialRules().get(0).getMaterial());
		assertEquals(someMaterial2, plan.getMaterialRules().get(1).getMaterial());
	}
	
	@Test(expected=InvalidMaterialRuleException.class)
	public void shouldNotAcceptARepeatedMaterialRuleForMoney() throws InvalidMaterialRuleException {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		Material someMaterial = new MaterialBuilder(table).instance();
		
		plan.addMaterialRule(someMaterial, new Money(10));
		plan.addMaterialRule(someMaterial, new Money(20));
	}
	
	@Test(expected=InvalidMaterialRuleException.class)
	public void shouldNotAcceptARepeatedMaterialRuleForCHs() throws InvalidMaterialRuleException {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		Material someMaterial = new MaterialBuilder(table).instance();
		
		plan.addMaterialRule(someMaterial, new CH(10));
		plan.addMaterialRule(someMaterial, new CH(20));
	}
	
	@Test
	public void shouldRemoveAMaterialRule() throws InvalidMaterialRuleException {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		Material someMaterial = new MaterialBuilder(table).instance();
		
		plan.addMaterialRule(someMaterial, new CH(10));
		
		assertEquals(1, plan.getMaterialRules().size());
		plan.removeMaterialRuleById(plan.getMaterialRules().get(0).getId());
		assertEquals(0, plan.getMaterialRules().size());		
	}

}
