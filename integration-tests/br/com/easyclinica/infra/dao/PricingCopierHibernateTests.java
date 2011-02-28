package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.MaterialBuilder;
import br.com.easyclinica.tests.helpers.MedicineBuilder;
import br.com.easyclinica.tests.helpers.ProcedureBuilder;
import br.com.easyclinica.tests.helpers.SpecialtyBuilder;

public class PricingCopierHibernateTests extends BaseIntegrationTests {

	private PricingCopierHibernate copier;
	private HealthCarePlan from;
	private Specialty specialty;
	private Procedure procedure;
	private Medicine medicine;
	private Material material;
	private HealthCarePlan to;

	@Before
	public void setUp() {
		copier = new PricingCopierHibernate(session);
		
		material = new MaterialBuilder().instance();
		medicine = new MedicineBuilder().instance();
		procedure = new ProcedureBuilder().instance();
		specialty = new SpecialtyBuilder().instance();
		
		from = new HealthCarePlanBuilder().instance();
		from.addPrecifiedMaterial(material, new BigDecimal("400.0"));
		from.addPrecifiedMedicine(medicine, new BigDecimal("100.0"));
		from.addPrecifiedSpecialty(specialty, new BigDecimal("200.0"));
		from.addPrecifiedProcedure(procedure, new BigDecimal("300.0"));
		
		to = new HealthCarePlanBuilder().instance();

		session.save(material);
		session.save(medicine);
		session.save(procedure);
		session.save(specialty);
		session.save(from);
		session.save(to);
	}

	@Test
	public void shouldCopyMedicines() {
		copier.copyPrices(from, to);

		session.refresh(to);
		
		assertEquals(1, to.getPrecifiedMedicines().size());
		assertTrue(new BigDecimal("100.0").compareTo(to.getPrecifiedMedicines().get(0).getAmount())==0);
	}
	
	@Test
	public void shouldCopySpecialties() {
		copier.copyPrices(from, to);

		session.refresh(to);
		
		assertEquals(1, to.getPrecifiedSpecialties().size());
		assertTrue(new BigDecimal("200.0").compareTo(to.getPrecifiedSpecialties().get(0).getAmount())==0);
	}
	
	@Test
	public void shouldCopyProcedures() {
		copier.copyPrices(from, to);

		session.refresh(to);
		
		assertEquals(1, to.getPrecifiedProcedures().size());
		assertTrue(new BigDecimal("300.0").compareTo(to.getPrecifiedProcedures().get(0).getFixedAmount())==0);
	}
	@Test
	public void shouldCopyMaterials() {
		copier.copyPrices(from, to);

		session.refresh(to);
		
		assertEquals(1, to.getPrecifiedMaterials().size());
		assertTrue(new BigDecimal("400.0").compareTo(to.getPrecifiedMaterials().get(0).getAmount())==0);
	}
}
