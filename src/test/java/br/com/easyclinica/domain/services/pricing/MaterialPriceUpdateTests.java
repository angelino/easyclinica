package br.com.easyclinica.domain.services.pricing;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;

public class MaterialPriceUpdateTests{

	private MaterialPriceUpdate update;

	@Before
	public void setUp() {
		update = new MaterialPriceUpdate();
	}
	
	@Test
	public void shouldUpdateMaterialValueIfItAlreadyExists() {
		
		HealthCarePlan plan = new HealthCarePlanBuilder().withPrecifiedMaterial(new Material(1), new BigDecimal("100.0")).instance();
		
		ImportedStuff material = new ImportedStuff(1, "material", new BigDecimal("150.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(material));
		
		assertEquals(new BigDecimal("150.0").doubleValue(), plan.getPrecifiedMaterials().get(0).getAmount().doubleValue(), 0.00001);
	}
	}
