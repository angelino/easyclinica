package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;

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

public class PricingZerifierHibernateTests extends DaoBase {

	private PricingZerifierHibernate zerifier;
	private HealthCarePlan plan;

	@Before
	public void setUp() {
		zerifier = new PricingZerifierHibernate(session);

		plan = new HealthCarePlanBuilder().instance();
		session.save(plan);
	}

	@Test
	public void shouldZeroMedicines() {
		Medicine m1 = new MedicineBuilder().withName("m1").instance();
		Medicine m2 = new MedicineBuilder().withName("m2").instance();
		session.save(m1);
		session.save(m2);

		zerifier.zeroPrices(plan);

		session.refresh(plan);

		assertEquals(2, plan.getPrecifiedMedicines().size());
		assertEquals(0.0, plan.getPrecifiedMedicines().get(0).getAmount()
				.doubleValue(), 0.00001);
		assertEquals(0.0, plan.getPrecifiedMedicines().get(1).getAmount()
				.doubleValue(), 0.00001);
	}

	@Test
	public void shouldZeroSpecialties() {
		Specialty m1 = new SpecialtyBuilder().withName("m1").instance();
		Specialty m2 = new SpecialtyBuilder().withName("m2").instance();
		session.save(m1);
		session.save(m2);

		zerifier.zeroPrices(plan);

		session.refresh(plan);

		assertEquals(2, plan.getPrecifiedSpecialties().size());
		assertEquals(0.0, plan.getPrecifiedSpecialties().get(0).getAmount()
				.doubleValue(), 0.00001);
		assertEquals(0.0, plan.getPrecifiedSpecialties().get(1).getAmount()
				.doubleValue(), 0.00001);
	}

	@Test
	public void shouldZeroProcedures() {
		Procedure m1 = new ProcedureBuilder().withName("m1").instance();
		Procedure m2 = new ProcedureBuilder().withName("m2").instance();
		session.save(m1);
		session.save(m2);

		zerifier.zeroPrices(plan);

		session.refresh(plan);

		assertEquals(2, plan.getPrecifiedProcedures().size());
		assertEquals(0.0, plan.getPrecifiedProcedures().get(0).getFixedAmount()
				.doubleValue(), 0.00001);
		assertEquals(0, plan.getPrecifiedProcedures().get(0).getCh());
		assertEquals(0.0, plan.getPrecifiedProcedures().get(1).getFixedAmount()
				.doubleValue(), 0.00001);
		assertEquals(0, plan.getPrecifiedProcedures().get(1).getCh());
	}

	@Test
	public void shouldZeroMaterials() {
		Material m1 = new MaterialBuilder().withName("m1").instance();
		Material m2 = new MaterialBuilder().withName("m2").instance();
		session.save(m1);
		session.save(m2);

		zerifier.zeroPrices(plan);

		session.refresh(plan);

		assertEquals(2, plan.getPrecifiedMaterials().size());
		assertEquals(0.0, plan.getPrecifiedMaterials().get(0).getAmount()
				.doubleValue(), 0.00001);
		assertEquals(0.0, plan.getPrecifiedMaterials().get(1).getAmount()
				.doubleValue(), 0.00001);
	}
}
