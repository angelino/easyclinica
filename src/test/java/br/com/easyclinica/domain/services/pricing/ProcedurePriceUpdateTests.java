package br.com.easyclinica.domain.services.pricing;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.services.pricing.update.ProcedurePriceUpdate;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;

public class ProcedurePriceUpdateTests{

	private ProcedurePriceUpdate update;

	@Before
	public void setUp() {
		update = new ProcedurePriceUpdate();
	}
	
	@Test
	public void shouldUpdateProcedureValueIfItAlreadyExists() {
		
		HealthCarePlan plan = new HealthCarePlanBuilder().withPrecifiedProcedure(new Procedure(1), new BigDecimal("100.0")).instance();
		
		ImportedStuff procedure = new ImportedStuff(1, "procedure", new BigDecimal("150.0"), 10, new BigDecimal("500.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(procedure));
		
		assertEquals(new BigDecimal("500.0").doubleValue(), plan.getPrecifiedProcedures().get(0).getRoomTaxAmount().doubleValue(), 0.00001);
		assertEquals(new BigDecimal("150.0").doubleValue(), plan.getPrecifiedProcedures().get(0).getFixedAmount().doubleValue(), 0.00001);
		assertEquals(10, plan.getPrecifiedProcedures().get(0).getCh());
	}
	
}
