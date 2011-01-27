package br.com.easyclinica.domain.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppointmentMedicineTests {

	@Test
	public void shouldCalculateTotalAmount() {
		AppointmentMedicine am = new AppointmentMedicine();
		am.setQty(2.0F);
		am.setUnitAmount(10.0);
		
		assertEquals(20, am.getTotalAmount(), 0.000001);
	}
}
