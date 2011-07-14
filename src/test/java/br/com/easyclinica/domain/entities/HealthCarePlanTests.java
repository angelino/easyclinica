package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

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
	
	@Test
	public void shouldAddPrecifiedProcedureByAmount() {
		HealthCarePlan plan = new HealthCarePlanBuilder().active().instance();
		
		PrecifiedProcedure pp = plan.addPrecifiedProcedure(new Procedure(), new BigDecimal("123.45"), new BigDecimal("55.6"));
		
		assertEquals(123.45, pp.getFixedAmount().doubleValue(), 0.0001);
		assertEquals(55.6, pp.getRoomTaxAmount().doubleValue(), 0.0001);
		assertEquals(0, pp.getCh());
	}
	
	@Test
	public void shouldAddPrecifiedProcedureByCh() {
		HealthCarePlan plan = new HealthCarePlanBuilder().active().instance();
		
		PrecifiedProcedure pp = plan.addPrecifiedProcedure(new Procedure(), 10, new BigDecimal("55.6"));
		
		assertEquals(0.0, pp.getFixedAmount().doubleValue(), 0.0001);
		assertEquals(55.6, pp.getRoomTaxAmount().doubleValue(), 0.0001);
		assertEquals(10, pp.getCh());
	}
}
