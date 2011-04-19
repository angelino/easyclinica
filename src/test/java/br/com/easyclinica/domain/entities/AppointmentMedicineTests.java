package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class AppointmentMedicineTests {

	@Test
	public void shouldCalculateTotalAmount() {
		AppointmentMedicine am = new AppointmentMedicine();
		am.setQty(new BigDecimal(2.0));
		am.setUnitAmount(new BigDecimal(10.0));
		
		assertEquals(new BigDecimal(20.0), am.getTotalAmount());
	}
}
