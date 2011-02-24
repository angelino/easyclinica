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
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllMedicines;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.MedicineBuilder;

public class MedicinePriceUpdateTests{

	private MedicinePriceUpdate update;
	private AllMedicines Medicines;

	@Before
	public void setUp() {
		Medicines = mock(AllMedicines.class);
		update = new MedicinePriceUpdate(Medicines);
	}
	
	@Test
	public void shouldUpdateMedicineValueIfItAlreadyExists() {
		
		PrecifiedMedicine pm = aPrecifiedMedicine(1, "100.0");		
		HealthCarePlan plan = new HealthCarePlanBuilder().withPrecifiedMedicine(pm).instance();
		
		ImportedStuff Medicine = new ImportedStuff(1, new BigDecimal("150.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(Medicine));
		
		assertEquals(new BigDecimal("150.0"), plan.getPrecifiedMedicines().get(0).getAmount());
	}
	
	@Test
	public void shouldCreateMedicineValueIfItDoesntAlreadyExists() {
		when(Medicines.getById(1)).thenReturn(new MedicineBuilder(1).instance());
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		
		ImportedStuff Medicine = new ImportedStuff(1, new BigDecimal("150.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(Medicine));
		
		assertNotNull(plan.getPrecifiedMedicines().get(0));
		assertEquals(new BigDecimal("150.0"), plan.getPrecifiedMedicines().get(0).getAmount());
	}
	

	private PrecifiedMedicine aPrecifiedMedicine(int id, String price) {
		PrecifiedMedicine pm = new PrecifiedMedicine();
		pm.setAmount(new BigDecimal(price));
		
		Medicine Medicine = new Medicine(id);
		pm.setMedicine(Medicine);
		
		return pm;
	}
}
