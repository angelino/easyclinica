package br.com.easyclinica.domain.services.pricing;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllProcedures;
import br.com.easyclinica.domain.services.pricing.update.ProcedurePriceUpdate;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import static org.mockito.Mockito.*;

public class ProcedurePriceUpdateTests{

	private ProcedurePriceUpdate update;
	private AllProcedures allProcedures;

	@Before
	public void setUp() {
		allProcedures = mock(AllProcedures.class);
		update = new ProcedurePriceUpdate(allProcedures);
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
	
	@Test
	public void shouldCreateProcedureValueIfItDoesNotExist() {
		
		HealthCarePlan plan = new HealthCarePlanBuilder().withPrecifiedProcedure(new Procedure(1), new BigDecimal("100.0")).instance();
		
		when(allProcedures.getById(2)).thenReturn(new Procedure(2));
		
		ImportedStuff procedure = new ImportedStuff(2, "procedure", new BigDecimal("150.0"), 0, new BigDecimal("500.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(procedure));
		
		assertEquals(new BigDecimal("500.0").doubleValue(), plan.getPrecifiedProcedures().get(1).getRoomTaxAmount().doubleValue(), 0.00001);
		assertEquals(new BigDecimal("150.0").doubleValue(), plan.getPrecifiedProcedures().get(1).getFixedAmount().doubleValue(), 0.00001);
	}
	

	@Test
	public void shouldCreateProcedureChIfItDoesNotExist() {
		
		HealthCarePlan plan = new HealthCarePlanBuilder().withPrecifiedProcedure(new Procedure(1), new BigDecimal("100.0")).instance();
		
		when(allProcedures.getById(2)).thenReturn(new Procedure(2));
		
		ImportedStuff procedure = new ImportedStuff(2, "procedure", new BigDecimal("0.0"), 10, new BigDecimal("500.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(procedure));
		
		assertEquals(new BigDecimal("500.0").doubleValue(), plan.getPrecifiedProcedures().get(1).getRoomTaxAmount().doubleValue(), 0.00001);
		assertEquals(10, plan.getPrecifiedProcedures().get(1).getCh());
	}
	
}
