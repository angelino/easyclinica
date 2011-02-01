package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		
		appointment.recalculate();
		
		assertEquals(10, appointment.getProcedureAmount(), 0.000001);
	}

	private AppointmentMaterial aMaterial() {
		AppointmentMaterial material = new AppointmentMaterial();
		material.setQty(1);
		material.setUnitAmount(10.0);
		return material;
	}
}
