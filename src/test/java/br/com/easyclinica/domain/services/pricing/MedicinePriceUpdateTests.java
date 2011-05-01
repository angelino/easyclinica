package br.com.easyclinica.domain.services.pricing;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;

public class MedicinePriceUpdateTests {

	private MedicinePriceUpdate update;

	@Before
	public void setUp() {
		update = new MedicinePriceUpdate();
	}

	@Test
	public void shouldUpdateMedicineValue() {

		HealthCarePlan plan = new HealthCarePlanBuilder()
				.withPrecifiedMedicine(new Medicine(1), new BigDecimal("100.0"))
				.instance();

		ImportedStuff medicine = new ImportedStuff(1, "medicine",
				new BigDecimal("150.0"));
		update.pricesForAHealthCarePlan(plan, Arrays.asList(medicine));

		assertEquals(new BigDecimal("150.0").doubleValue(), plan
				.getPrecifiedMedicines().get(0).getAmount().doubleValue(),
				0.00001);
	}
}
