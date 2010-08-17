package br.com.easyclinica.actions;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.Service;
import br.com.easyclinica.domain.entities.ServicesTable;
import br.com.easyclinica.domain.exceptions.InvalidMaterialRuleException;
import br.com.easyclinica.domain.exceptions.InvalidServiceRuleException;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.MaterialBuilder;
import br.com.easyclinica.tests.helpers.ServiceBuilder;
import br.com.easyclinica.view.Messages;

public class HealthCarePlanRuleControllerTest {

	private AllHealthCarePlans allHealthCarePlans;
	private MockResult result;
	private HealthCarePlanRuleController controller;
	private ServicesTable table;

	@Before
	public void setUp() {
		result = new MockResult();
		allHealthCarePlans = mock(AllHealthCarePlans.class);
		controller = new HealthCarePlanRuleController(allHealthCarePlans, result);
		
		table = new ServicesTable(new Name("table"));
	}
	
	@Test
	public void shouldAddNewCHServiceRule() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		when(allHealthCarePlans.getById(1)).thenReturn(plan);
		
		Service service = new ServiceBuilder(table).instance();
		controller.saveServiceRule(1, service, new CH(10), Money.zero());
		
		assertEquals(new CH(10), plan.getServiceRules().get(0).getCh());
		assertEquals(service.getName(), plan.getServiceRules().get(0).getService().getName());
		assertEquals(Messages.HEALTH_CARE_PLAN_SERVICE_RULE_ADDED, result.included("message"));
	}
	
	@Test
	public void shouldAddNewMoneyServiceRule() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		when(allHealthCarePlans.getById(1)).thenReturn(plan);
		
		Service service = new ServiceBuilder(table).instance();
		controller.saveServiceRule(1, service, CH.zero(), new Money(10));
		
		assertEquals(new Money(10), plan.getServiceRules().get(0).getValue());
		assertEquals(service.getName(), plan.getServiceRules().get(0).getService().getName());
		assertEquals(Messages.HEALTH_CARE_PLAN_SERVICE_RULE_ADDED, result.included("message"));
	}
	
	@Test
	public void shouldNotAddRepeatedServiceRule() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		when(allHealthCarePlans.getById(1)).thenReturn(plan);
		
		Service service = new ServiceBuilder(table).instance();
		controller.saveServiceRule(1, service, CH.zero(), new Money(10));
		controller.saveServiceRule(1, service, CH.zero(), new Money(10));
		
		assertEquals(Messages.HEALTH_CARE_PLAN_REPEATED_SERVICE_RULE, result.included("message"));
	}
	
	@Test
	public void shouldDeleteAServiceRule() throws InvalidServiceRuleException {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		Service service = new ServiceBuilder(table).instance();
		plan.addServiceRule(service, new CH(10));
		
		when(allHealthCarePlans.getById(1)).thenReturn(plan);
		
		controller.deleteServiceRule(1, plan.getServiceRules().get(0).getId());
		
		assertEquals(0, plan.getServiceRules().size());
		assertEquals(Messages.HEALTH_CARE_PLAN_SERVICE_RULE_REMOVED, result.included("message"));
	}
	
	@Test
	public void shouldAddNewCHMaterialRule() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		when(allHealthCarePlans.getById(1)).thenReturn(plan);
		
		Material material = new MaterialBuilder(table).instance();
		controller.saveMaterialRule(1, material, new CH(10), Money.zero());
		
		assertEquals(new CH(10), plan.getMaterialRules().get(0).getCh());
		assertEquals(material.getName(), plan.getMaterialRules().get(0).getMaterial().getName());
		assertEquals(Messages.HEALTH_CARE_PLAN_MATERIAL_RULE_ADDED, result.included("message"));
	}
	
	@Test
	public void shouldAddNewMoneyMaterialRule() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		when(allHealthCarePlans.getById(1)).thenReturn(plan);
		
		Material material = new MaterialBuilder(table).instance();
		controller.saveMaterialRule(1, material, CH.zero(), new Money(10));
		
		assertEquals(new Money(10), plan.getMaterialRules().get(0).getValue());
		assertEquals(material.getName(), plan.getMaterialRules().get(0).getMaterial().getName());
		assertEquals(Messages.HEALTH_CARE_PLAN_MATERIAL_RULE_ADDED, result.included("message"));
	}
	
	@Test
	public void shouldNotAddRepeatedMaterialRule() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		when(allHealthCarePlans.getById(1)).thenReturn(plan);
		
		Material material = new MaterialBuilder(table).instance();
		controller.saveMaterialRule(1, material, CH.zero(), new Money(10));
		controller.saveMaterialRule(1, material, CH.zero(), new Money(10));
		
		assertEquals(Messages.HEALTH_CARE_PLAN_REPEATED_MATERIAL_RULE, result.included("message"));
	}
	
	@Test
	public void shouldDeleteAMaterialRule() throws InvalidMaterialRuleException {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		Material material = new MaterialBuilder(table).instance();
		plan.addMaterialRule(material, new CH(10));
		
		when(allHealthCarePlans.getById(1)).thenReturn(plan);
		
		controller.deleteMaterialRule(1, plan.getMaterialRules().get(0).getId());
		
		assertEquals(0, plan.getMaterialRules().size());
		assertEquals(Messages.HEALTH_CARE_PLAN_MATERIAL_RULE_REMOVED, result.included("message"));
	}

}
