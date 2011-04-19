package br.com.easyclinica.domain.services.pricing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllProcedures;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.ProcedureBuilder;

public class ProcedurePriceUpdateTests{

	private ProcedurePriceUpdate update;
	private AllProcedures Procedures;

	@Before
	public void setUp() {
		Procedures = mock(AllProcedures.class);
		update = new ProcedurePriceUpdate(Procedures);
	}
	
	@Test
	public void shouldUpdateProcedureValueIfItAlreadyExists() {
		
		HealthCarePlan plan = new HealthCarePlanBuilder().withPrecifiedProcedure(new Procedure(1), new BigDecimal("100.0")).instance();
		
		ImportedStuff Procedure = new ImportedStuff(1, "procedure", new BigDecimal("150.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(Procedure));
		
		assertEquals(new BigDecimal("150.0"), plan.getPrecifiedProcedures().get(0).getFixedAmount());
	}
	
	@Test
	public void shouldCreateProcedureValueIfItDoesntAlreadyExists() {
		when(Procedures.getById(1)).thenReturn(new ProcedureBuilder(1).instance());
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		
		ImportedStuff Procedure = new ImportedStuff(1, "procedure", new BigDecimal("150.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(Procedure));
		
		assertNotNull(plan.getPrecifiedProcedures().get(0));
		assertEquals(new BigDecimal("150.0"), plan.getPrecifiedProcedures().get(0).getFixedAmount());
	}
}
