package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.MaterialWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.builders.MaterialWithPriceAndQuantityBuilder;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.MaterialBuilder;
import br.com.easyclinica.tests.helpers.ProcedureBuilder;

public class MaterialDaoTests extends DaoBase {
	private MaterialDao dao;
	private MaterialWithPriceAndQuantityBuilder materialWithPriceAndQuantityBuilder;
	
	@Before
	public void setUp() {
		dao = new MaterialDao(session);
	
		materialWithPriceAndQuantityBuilder = new MaterialWithPriceAndQuantityBuilder(dao);
	}
		
	@Test
	public void shouldGetMaterialsWithPriceAndQuantity() {
		Procedure procedure = aSavedProcedure();
		HealthCarePlan healthCarePlan = aSavedHealthCarePlan();
		
		Material material1 = aSavedMaterial();
		procedure.addMaterial(material1, new BigDecimal(1));
		Material material2 = aSavedMaterial();
		procedure.addMaterial(material2, new BigDecimal(2));
		session.save(procedure);
		
		List<MaterialWithPriceAndQuantity> materials = materialWithPriceAndQuantityBuilder.inTheHealthCarePlan(healthCarePlan)
																						  .ofTheProcedure(procedure)
																						  .execute();
		
		assertEquals(2, materials.size());
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
