package br.com.easyclinica.actions;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Service;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.ServiceBuilder;
import br.com.easyclinica.view.Messages;

public class HealthCarePlanRuleControllerTest {

	private AllHealthCarePlans allHealthCarePlans;
	private MockResult result;
	private HealthCarePlanRuleController controller;

	@Before
	public void setUp() {
		result = new MockResult();
		allHealthCarePlans = mock(AllHealthCarePlans.class);
		controller = new HealthCarePlanRuleController(allHealthCarePlans, result);
	}
	
	@Test
	public void shouldAddNewCHServiceRule() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		when(allHealthCarePlans.getById(1)).thenReturn(plan);
		
		Service service = new ServiceBuilder().instance();
		controller.saveServiceRule(1, service, new CH(10), Money.zero());
		
		assertEquals(new CH(10), plan.getServiceRules().get(0).getCh());
		assertEquals(service.getName(), plan.getServiceRules().get(0).getService().getName());
		verify(allHealthCarePlans).update(plan);
		assertEquals(Messages.HEALTH_CARE_PLAN_SERVICE_RULE_ADDED, result.included("message"));
	}
	
	@Test
	public void shouldAddNewMoneyServiceRule() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		when(allHealthCarePlans.getById(1)).thenReturn(plan);
		
		Service service = new ServiceBuilder().instance();
		controller.saveServiceRule(1, service, CH.zero(), new Money(10));
		
		assertEquals(new Money(10), plan.getServiceRules().get(0).getValue());
		assertEquals(service.getName(), plan.getServiceRules().get(0).getService().getName());
		verify(allHealthCarePlans).update(plan);
		assertEquals(Messages.HEALTH_CARE_PLAN_SERVICE_RULE_ADDED, result.included("message"));
	}
}
