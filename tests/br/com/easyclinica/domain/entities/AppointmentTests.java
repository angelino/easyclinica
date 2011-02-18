package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Quantity;

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
		
		assertEquals(new Money(10).getAmount().doubleValue(), appointment.getProcedureAmount().getAmount().doubleValue(), 0.00001);
	}

	private AppointmentMaterial aMaterial() {
		AppointmentMaterial material = new AppointmentMaterial();
		material.setQty(new Quantity(1));
		material.setUnitAmount(new Money(10.0));
		return material;
	}
}
