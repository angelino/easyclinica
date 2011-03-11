package br.com.easyclinica.domain.entities.builders;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.MaterialWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.infra.dao.DaoBase;
import br.com.easyclinica.infra.dao.MaterialDao;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.MaterialBuilder;
import br.com.easyclinica.tests.helpers.ProcedureBuilder;

public class MaterialWithPriceAndQuantityBuilderTests extends DaoBase {
	
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
		procedure.addMaterial(material1, new BigDecimal("1.00"));
		Material material2 = aSavedMaterial();
		procedure.addMaterial(material2, new BigDecimal("2.00"));
		session.save(procedure);
		
		PrecifiedMaterial precifiedMaterial1 = new PrecifiedMaterial();
		precifiedMaterial1.setAmount(new BigDecimal("10.20"));
		precifiedMaterial1.setHealthCarePlan(healthCarePlan);
		precifiedMaterial1.setMaterial(material1);
		session.save(precifiedMaterial1);
		
		PrecifiedMaterial precifiedMaterial2 = new PrecifiedMaterial();
		precifiedMaterial2.setAmount(new BigDecimal("10.30"));
		precifiedMaterial2.setHealthCarePlan(healthCarePlan);
		precifiedMaterial2.setMaterial(material2);
		session.save(precifiedMaterial2);
		
		List<MaterialWithPriceAndQuantity> materials = dao.getMaterialsWithPriceAndQuantity(procedure, healthCarePlan);
		
		assertEquals(materials.size(), 2);
		assertEquals(materials.get(0).getAmount(), precifiedMaterial1.getAmount());
		assertEquals(materials.get(1).getAmount(), precifiedMaterial2.getAmount());
		assertEquals(materials.get(0).getQty(), procedure.getMaterials().get(0).getQty());
		assertEquals(materials.get(1).getQty(), procedure.getMaterials().get(1).getQty());
	}
	
	private Procedure aSavedProcedure() {
		Procedure procedure = new ProcedureBuilder().instance();
		session.save(procedure);
		return procedure;
	}
	
	private HealthCarePlan aSavedHealthCarePlan() {
		HealthCarePlan plan = new HealthCarePlanBuilder().withName("bla").withCh(new BigDecimal(10)).instance();
		session.save(plan);
		return plan;
	}
	
	private Material aSavedMaterial() {
		Material material = new MaterialBuilder().instance();
		session.save(material);
		return material;
	}
}
