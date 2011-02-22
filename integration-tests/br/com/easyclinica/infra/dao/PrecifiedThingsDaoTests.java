package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;
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

public class PrecifiedThingsDaoTests extends BaseIntegrationTests {
	
	private PrecifiedThingsDao dao;
	
	@Before
	public void setUp() {
		dao = new PrecifiedThingsDao(session);
	}
	
	@Test
	public void shouldGetTheFixedPriceOfTheProcedure() { 
		Procedure procedure = aSavedProcedure();
		HealthCarePlan healthCarePlan = aSavedHealthCarePlan();
		
		PrecifiedProcedure precifiedProcedure = new PrecifiedProcedure();
		precifiedProcedure.setFixedAmount(new BigDecimal(10.67));
		precifiedProcedure.setProcedure(procedure);
		precifiedProcedure.setHealthCarePlan(healthCarePlan);
		session.save(precifiedProcedure);
		
		PrecifiedProcedure persistedPrecifiedProcedure = dao.getPrice(procedure, healthCarePlan);
		
		assertTrue((persistedPrecifiedProcedure.getFixedAmount() == precifiedProcedure.getFixedAmount()));
	}
	
	@Test
	public void shouldGetTheProceduresMaterialsPrice() {
		Procedure procedure = aSavedProcedure();
		HealthCarePlan healthCarePlan = aSavedHealthCarePlan();
		
		Material material1 = aSavedMaterial();
		procedure.addMaterial(material1, new BigDecimal(1));
		Material material2 = aSavedMaterial();
		procedure.addMaterial(material2, new BigDecimal(2));
		session.save(procedure);
		
		PrecifiedMaterial precifiedMaterial1 = new PrecifiedMaterial();
		precifiedMaterial1.setAmount(new BigDecimal(10.20));
		precifiedMaterial1.setHealthCarePlan(healthCarePlan);
		precifiedMaterial1.setMaterial(material1);
		session.save(precifiedMaterial1);
		
		PrecifiedMaterial precifiedMaterial2 = new PrecifiedMaterial();
		precifiedMaterial2.setAmount(new BigDecimal(10.30));
		precifiedMaterial2.setHealthCarePlan(healthCarePlan);
		precifiedMaterial2.setMaterial(material2);
		session.save(precifiedMaterial2);
		
		List<PrecifiedMaterial> materials = dao.getMaterialsPrice(procedure, healthCarePlan);
		
		assertEquals(materials.size(), 2);
		assertTrue(materials.get(0).getAmount() == precifiedMaterial1.getAmount());
		assertTrue(materials.get(1).getAmount() == precifiedMaterial2.getAmount());
	}
	
	@Test
	public void shouldGetTheProceduresMedicinePrice() {
		Procedure procedure = aSavedProcedure();
		HealthCarePlan healthCarePlan = aSavedHealthCarePlan();
		
		Medicine medicine1 = aSavedMedicine();
		procedure.addMedicine(medicine1, new BigDecimal(1));
		Medicine medicine2 = aSavedMedicine();
		procedure.addMedicine(medicine2, new BigDecimal(2));
		session.save(procedure);
		
		PrecifiedMedicine precifiedMedicine1 = new PrecifiedMedicine();
		precifiedMedicine1.setAmount(new BigDecimal(10.20));
		precifiedMedicine1.setHealthCarePlan(healthCarePlan);
		precifiedMedicine1.setMedicine(medicine1);
		session.save(precifiedMedicine1);
		
		PrecifiedMedicine precifiedMedicine2 = new PrecifiedMedicine();
		precifiedMedicine2.setAmount(new BigDecimal(10.30));
		precifiedMedicine2.setHealthCarePlan(healthCarePlan);
		precifiedMedicine2.setMedicine(medicine2);
		session.save(precifiedMedicine2);
		
		List<PrecifiedMedicine> medicine = dao.getMedicinePrice(procedure, healthCarePlan);
		
		assertEquals(medicine.size(), 2);
		assertEquals(medicine.get(0).getAmount(), precifiedMedicine1.getAmount());
		assertEquals(medicine.get(1).getAmount(), precifiedMedicine2.getAmount());
	}
	
	@Test
	public void shouldGetMedicalAppointmentPrice() {
		HealthCarePlan healthCarePlan = aSavedHealthCarePlan();
		Specialty specialty = aSavedSpecialty();
		
		PrecifiedSpecialty precifiedSpecialty = new PrecifiedSpecialty();
		precifiedSpecialty.setAmount(new BigDecimal("30.50"));
		precifiedSpecialty.setHealthCarePlan(healthCarePlan);
		precifiedSpecialty.setSpecialty(specialty);
		session.save(precifiedSpecialty);
		
		PrecifiedSpecialty persistedPrecifiedSpecialty = dao.getMedicalAppointmentPrice(specialty, healthCarePlan);
		
		assertTrue((persistedPrecifiedSpecialty.getAmount() == precifiedSpecialty.getAmount()));
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
	
	private Medicine aSavedMedicine() {
		Medicine medicine = new MedicineBuilder().instance();
		session.save(medicine);
		return medicine;
	}
	
	private Specialty aSavedSpecialty() {
		Specialty specialty = new SpecialtyBuilder().instance();
		session.save(specialty);
		return specialty;
	}
}
