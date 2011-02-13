package br.com.easyclinica.domain.entities.builders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.MaterialWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Quantity;
import br.com.easyclinica.infra.dao.BaseIntegrationTests;
import br.com.easyclinica.infra.dao.MaterialDao;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.MaterialBuilder;
import br.com.easyclinica.tests.helpers.ProcedureBuilder;

public class MaterialWithPriceAndQuantityBuilderTests extends BaseIntegrationTests {
	
	private MaterialDao dao;
	
	@Before
	public void setUp() {
		dao = new MaterialDao(session);
	}
	
	@Test
	public void shouldGetQuantityAndPriceOfProceduresMaterials() {
		Procedure procedure = aSavedProcedure();
		HealthCarePlan healthCarePlan = aSavedHealthCarePlan();
		
		Material material1 = aSavedMaterial();
		procedure.addMaterial(material1, new Quantity(1));
		Material material2 = aSavedMaterial();
		procedure.addMaterial(material2, new Quantity(2));
		session.save(procedure);
		
		PrecifiedMaterial precifiedMaterial1 = new PrecifiedMaterial();
		precifiedMaterial1.setAmount(new Money(10.20));
		precifiedMaterial1.setHealthCarePlan(healthCarePlan);
		precifiedMaterial1.setMaterial(material1);
		session.save(precifiedMaterial1);
		
		PrecifiedMaterial precifiedMaterial2 = new PrecifiedMaterial();
		precifiedMaterial2.setAmount(new Money(10.30));
		precifiedMaterial2.setHealthCarePlan(healthCarePlan);
		precifiedMaterial2.setMaterial(material2);
		session.save(precifiedMaterial2);
		
		List<MaterialWithPriceAndQuantity> materials = dao.getMaterialsWithPriceAndQuantity(procedure, healthCarePlan);
		
		assertEquals(materials.size(), 2);
		assertTrue(materials.get(0).getAmount().getAmount().doubleValue() == precifiedMaterial1.getAmount().getAmount().doubleValue());
		assertTrue(materials.get(1).getAmount().getAmount().doubleValue() == precifiedMaterial2.getAmount().getAmount().doubleValue());
		assertTrue(materials.get(0).getQty().getQty().doubleValue() == procedure.getMaterials().get(0).getQty().getQty().doubleValue());
		assertTrue(materials.get(1).getQty().getQty().doubleValue() == procedure.getMaterials().get(1).getQty().getQty().doubleValue());
	}
	
	private Procedure aSavedProcedure() {
		Procedure procedure = new ProcedureBuilder().instance();
		session.save(procedure);
		return procedure;
	}
	
	private HealthCarePlan aSavedHealthCarePlan() {
		HealthCarePlan plan = new HealthCarePlanBuilder().withName("bla").withCh(10).instance();
		session.save(plan);
		return plan;
	}
	
	private Material aSavedMaterial() {
		Material material = new MaterialBuilder().instance();
		session.save(material);
		return material;
	}
}
