package br.com.easyclinica.domain.services.pricing;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.services.pricing.update.SpecialtyPriceUpdate;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;

public class SpecialtyPriceUpdateTests{

	private SpecialtyPriceUpdate update;

	@Before
	public void setUp() {
		update = new SpecialtyPriceUpdate();
	}
	
	@Test
	public void shouldUpdateSpecialtyValueIfItAlreadyExists() {
		
		HealthCarePlan plan = new HealthCarePlanBuilder().withPrecifiedSpecialty(new Specialty(1), new BigDecimal("100.0")).instance();
		
		ImportedStuff Specialty = new ImportedStuff(1, "specialty", new BigDecimal("150.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(Specialty));
		
		assertEquals(new BigDecimal("150.0").doubleValue(), plan.getPrecifiedSpecialties().get(0).getAmount().doubleValue(), 0.00001);
	}
	
}
