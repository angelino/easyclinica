package br.com.easyclinica.domain.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppointmentTests {

	@Test
	public void mayBeAReturnAppointment() {
		Appointment appointment = new Appointment();
		
		assertFalse(appointment.isReturn());
		
		appointment.markAsReturn();
		
		assertTrue(appointment.isReturn());
		
	}
}
