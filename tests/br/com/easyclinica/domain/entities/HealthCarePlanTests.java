package br.com.easyclinica.domain.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;


public class HealthCarePlanTests {

	@Test
	public void shouldActivate() {
		HealthCarePlan plan = new HealthCarePlanBuilder().notActive().instance();
		
		assertFalse(plan.isActive());
		
		plan.activate();
		
		assertTrue(plan.isActive());
		
	}
	
	@Test
	public void shouldDeactivate() {
		HealthCarePlan plan = new HealthCarePlanBuilder().active().instance();
		
		assertTrue(plan.isActive());
		
		plan.deactivate();
		
		assertFalse(plan.isActive());
		
	}
}
