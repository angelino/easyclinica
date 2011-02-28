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
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllSpecialties;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.SpecialtyBuilder;

public class SpecialtyPriceUpdateTests{

	private SpecialtyPriceUpdate update;
	private AllSpecialties Specialties;

	@Before
	public void setUp() {
		Specialties = mock(AllSpecialties.class);
		update = new SpecialtyPriceUpdate(Specialties);
	}
	
	@Test
	public void shouldUpdateSpecialtyValueIfItAlreadyExists() {
		
		HealthCarePlan plan = new HealthCarePlanBuilder().withPrecifiedSpecialty(new Specialty(1), new BigDecimal("100.0")).instance();
		
		ImportedStuff Specialty = new ImportedStuff(1, "specialty", new BigDecimal("150.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(Specialty));
		
		assertEquals(new BigDecimal("150.0"), plan.getPrecifiedSpecialties().get(0).getAmount());
	}
	
	@Test
	public void shouldCreateSpecialtyValueIfItDoesntAlreadyExists() {
		when(Specialties.getById(1)).thenReturn(new SpecialtyBuilder(1).instance());
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		
		ImportedStuff Specialty = new ImportedStuff(1, "specialty", new BigDecimal("150.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(Specialty));
		
		assertNotNull(plan.getPrecifiedSpecialties().get(0));
		assertEquals(new BigDecimal("150.0"), plan.getPrecifiedSpecialties().get(0).getAmount());
	}
}
