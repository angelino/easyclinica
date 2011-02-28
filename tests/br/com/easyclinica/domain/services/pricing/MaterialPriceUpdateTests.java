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
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllMaterials;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.MaterialBuilder;

public class MaterialPriceUpdateTests{

	private MaterialPriceUpdate update;
	private AllMaterials materials;

	@Before
	public void setUp() {
		materials = mock(AllMaterials.class);
		update = new MaterialPriceUpdate(materials);
	}
	
	@Test
	public void shouldUpdateMaterialValueIfItAlreadyExists() {
		
		HealthCarePlan plan = new HealthCarePlanBuilder().withPrecifiedMaterial(new Material(1), new BigDecimal("100.0")).instance();
		
		ImportedStuff material = new ImportedStuff(1, "material", new BigDecimal("150.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(material));
		
		assertEquals(new BigDecimal("150.0"), plan.getPrecifiedMaterials().get(0).getAmount());
	}
	
	@Test
	public void shouldCreateMaterialValueIfItDoesntAlreadyExists() {
		when(materials.getById(1)).thenReturn(new MaterialBuilder(1).instance());
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		
		ImportedStuff material = new ImportedStuff(1, "material", new BigDecimal("150.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(material));
		
		assertNotNull(plan.getPrecifiedMaterials().get(0));
		assertEquals(new BigDecimal("150.0"), plan.getPrecifiedMaterials().get(0).getAmount());
	}
	
}
