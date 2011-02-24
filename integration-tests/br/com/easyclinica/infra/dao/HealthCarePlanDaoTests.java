package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.MaterialBuilder;
import br.com.easyclinica.tests.helpers.MedicineBuilder;
import br.com.easyclinica.tests.helpers.ProcedureBuilder;
import br.com.easyclinica.tests.helpers.SpecialtyBuilder;

public class HealthCarePlanDaoTests extends BaseIntegrationTests {
	private HealthCarePlanDao dao;
	
	@Before
	public void setUp() {
		dao = new HealthCarePlanDao(session);
	}
	
	@Test
	public void shouldAdd() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		dao.add(plan);
		
		List<HealthCarePlan> list = dao.get();
		assertEquals(1, list.size());
		
		HealthCarePlan newOne = list.get(0);
		assertEquals(plan.getName(), newOne.getName());
		assertEquals(plan.getAddress().getStreet(), newOne.getAddress().getStreet());
		assertEquals(plan.getAddress().getNeighborhood(), newOne.getAddress().getNeighborhood());
		assertEquals(plan.getAddress().getPostalCode(), newOne.getAddress().getPostalCode());
		assertEquals(plan.getAddress().getCity(), newOne.getAddress().getCity());
		assertEquals(plan.getAddress().getState(), newOne.getAddress().getState());
		assertEquals(plan.getTelephone(), newOne.getTelephone());
		assertEquals(plan.getEmail(), newOne.getEmail());
		assertEquals(plan.getWebsite(), newOne.getWebsite());
		assertEquals(plan.getContact(), newOne.getContact());
		assertEquals(plan.getObservations(), newOne.getObservations());
		assertEquals(plan.getCh(), newOne.getCh());
		assertEquals(plan.isActive(), newOne.isActive());
	}

	@Test
	public void shouldUpdate() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		dao.add(plan);
		
		HealthCarePlan updatedPlan = new HealthCarePlanBuilder(plan.getId())
			.withName("new Amil")
			.instance();
		dao.update(updatedPlan);
		
		HealthCarePlan secondRetrievedPlan = dao.getById(plan.getId());
		assertNotNull(secondRetrievedPlan);
		assertEquals("new Amil", secondRetrievedPlan.getName());
	}
	
	@Test
	public void shouldGetById() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		dao.add(plan);
		
		HealthCarePlan retrievedPlan = dao.getById(plan.getId());
		
		assertNotNull(retrievedPlan);
		assertEquals(plan.getId(), retrievedPlan.getId());
	}
	
	@Test
	public void shouldCountElements() {
		HealthCarePlan firstPlan = new HealthCarePlanBuilder().instance();
		HealthCarePlan secondPlan = new HealthCarePlanBuilder().instance();
		
		dao.add(firstPlan);
		dao.add(secondPlan);
		
		assertEquals(2, dao.count());
	}
	
	@Test
	public void shouldPaginate() {
		HealthCarePlan firstPlan = new HealthCarePlanBuilder().withName("a").instance();
		HealthCarePlan secondPlan = new HealthCarePlanBuilder().withName("b").instance();
		
		dao.add(firstPlan);
		dao.add(secondPlan);
		
		assertEquals(firstPlan.getName(), dao.get(0, 1).get(0).getName());
	}
	
	@Test
	public void shouldUpdateAllMaterialPrices() {
		
		Material material1 = new MaterialBuilder().withName("m1").instance();
		Material material2 = new MaterialBuilder().withName("m2").instance();
		session.save(material1);
		session.save(material2);
		
		HealthCarePlan plan = new HealthCarePlanBuilder().withName("a").instance();
		session.save(plan);
		
		PrecifiedMaterial pm = new PrecifiedMaterial();
		pm.setAmount(new BigDecimal("100.0"));
		pm.setMaterial(material1);
		plan.addPrecifiedMaterial(pm);
		session.save(pm);
		
		PrecifiedMaterial pm2 = new PrecifiedMaterial();
		pm2.setAmount(new BigDecimal("150.0"));
		pm2.setMaterial(material1);
		plan.addPrecifiedMaterial(pm2);
		
		pm.setAmount(new BigDecimal("200.0"));
		dao.updatePrices(plan);
		
		assertTrue(new BigDecimal("200.0").compareTo(((PrecifiedMaterial)session.load(PrecifiedMaterial.class, pm.getId())).getAmount()) == 0);
		assertTrue(new BigDecimal("150.0").compareTo(((PrecifiedMaterial)session.load(PrecifiedMaterial.class, pm2.getId())).getAmount()) == 0);
	}
	
	@Test
	public void shouldUpdateAllMedicinePrices() {
		
		Medicine medicine1 = new MedicineBuilder().withName("m1").instance();
		Medicine medicine2 = new MedicineBuilder().withName("m2").instance();
		session.save(medicine1);
		session.save(medicine2);
		
		HealthCarePlan plan = new HealthCarePlanBuilder().withName("a").instance();
		session.save(plan);
		
		PrecifiedMedicine pm = new PrecifiedMedicine();
		pm.setAmount(new BigDecimal("100.0"));
		pm.setMedicine(medicine1);
		plan.addPrecifiedMedicine(pm);
		session.save(pm);
		
		PrecifiedMedicine pm2 = new PrecifiedMedicine();
		pm2.setAmount(new BigDecimal("150.0"));
		pm2.setMedicine(medicine1);
		plan.addPrecifiedMedicine(pm2);
		
		pm.setAmount(new BigDecimal("200.0"));
		dao.updatePrices(plan);
		
		assertTrue(new BigDecimal("200.0").compareTo(((PrecifiedMedicine)session.load(PrecifiedMedicine.class, pm.getId())).getAmount()) == 0);
		assertTrue(new BigDecimal("150.0").compareTo(((PrecifiedMedicine)session.load(PrecifiedMedicine.class, pm2.getId())).getAmount()) == 0);
	}
	
	@Test
	public void shouldUpdateAllSpecialtyPrices() {
		
		Specialty specialty1 = new SpecialtyBuilder().withName("m1").instance();
		Specialty specialty2 = new SpecialtyBuilder().withName("m2").instance();
		session.save(specialty1);
		session.save(specialty2);
		
		HealthCarePlan plan = new HealthCarePlanBuilder().withName("a").instance();
		session.save(plan);
		
		PrecifiedSpecialty ps = new PrecifiedSpecialty();
		ps.setAmount(new BigDecimal("100.0"));
		ps.setSpecialty(specialty1);
		plan.addPrecifiedSpecialty(ps);
		session.save(ps);
		
		PrecifiedSpecialty ps2 = new PrecifiedSpecialty();
		ps2.setAmount(new BigDecimal("150.0"));
		ps2.setSpecialty(specialty1);
		plan.addPrecifiedSpecialty(ps2);
		
		ps.setAmount(new BigDecimal("200.0"));
		dao.updatePrices(plan);
		
		assertTrue(new BigDecimal("200.0").compareTo(((PrecifiedSpecialty)session.load(PrecifiedSpecialty.class, ps.getId())).getAmount()) == 0);
		assertTrue(new BigDecimal("150.0").compareTo(((PrecifiedSpecialty)session.load(PrecifiedSpecialty.class, ps2.getId())).getAmount()) == 0);
	}
	
	@Test
	public void shouldUpdateAllProcedurePrices() {
		
		Procedure medicine1 = new ProcedureBuilder().withName("m1").instance();
		Procedure medicine2 = new ProcedureBuilder().withName("m2").instance();
		session.save(medicine1);
		session.save(medicine2);
		
		HealthCarePlan plan = new HealthCarePlanBuilder().withName("a").instance();
		session.save(plan);
		
		PrecifiedProcedure pp = new PrecifiedProcedure();
		pp.setFixedAmount(new BigDecimal("100.0"));
		pp.setProcedure(medicine1);
		plan.addPrecifiedProcedure(pp);
		session.save(pp);
		
		PrecifiedProcedure pp2 = new PrecifiedProcedure();
		pp2.setFixedAmount(new BigDecimal("150.0"));
		pp2.setProcedure(medicine1);
		plan.addPrecifiedProcedure(pp2);
		
		pp.setFixedAmount(new BigDecimal("200.0"));
		dao.updatePrices(plan);
		
		assertTrue(new BigDecimal("200.0").compareTo(((PrecifiedProcedure)session.load(PrecifiedProcedure.class, pp.getId())).getFixedAmount()) == 0);
		assertTrue(new BigDecimal("150.0").compareTo(((PrecifiedProcedure)session.load(PrecifiedProcedure.class, pp2.getId())).getFixedAmount()) == 0);
	}
}
