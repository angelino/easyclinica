package br.com.easyclinica.domain.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppointmentProcedureTests {

	@Test
	public void shouldCalculateTheTotalAmountOfTheProcedure(){
		AppointmentProcedure proc = new AppointmentProcedure();
		proc.addMaterial(aMaterial(2, 10.0));
		proc.addMaterial(aMaterial(1, 100.0));
		proc.addMedicine(aMedicine(2, 20.0));
		proc.addMedicine(aMedicine(3, 30.0));
		
		double total = 2*10 + 100 + 2*20 + 3*30;
		assertEquals(total, proc.getTotalAmount(), 0.0000001);
		
	}

	private AppointmentMaterial aMaterial(int qty, double amount) {
		AppointmentMaterial m = new AppointmentMaterial();
		m.setQty(qty);
		m.setUnitAmount(amount);
		return m;
	}
	
	private AppointmentMedicine aMedicine(int qty, double amount) {
		AppointmentMedicine m = new AppointmentMedicine();
		m.setQty(qty);
		m.setUnitAmount(amount);
		return m;
	}

}
