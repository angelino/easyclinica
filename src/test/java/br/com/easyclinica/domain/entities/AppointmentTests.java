package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class AppointmentTests {

	private Appointment appointment;

	@Before
	public void setUp() {
		appointment = new Appointment();		
	}
	
	@Test
	public void mayBeAReturnAppointment() {
		assertFalse(appointment.isReturn());
		
		appointment.markAsReturn();
		
		assertTrue(appointment.isReturn());
	}
	
	@Test
	public void shouldRecalculateTheTotalAmountOfTheAppointment() {
		AppointmentProcedure procedure = new AppointmentProcedure();
		procedure.addMaterial(aMaterial());
		appointment.addProcedure(procedure);
		
		assertEquals(new BigDecimal(10), appointment.getTotalAmount());
	}

	private AppointmentMaterial aMaterial() {
		AppointmentMaterial material = new AppointmentMaterial();
		material.setQty(new BigDecimal(1));
		material.setUnitAmount(new BigDecimal(10.0));
		return material;
	}
}
