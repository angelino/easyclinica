package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Quantity;

public class AppointmentProcedureTests {

	@Test
	public void shouldCalculateTheTotalAmountOfTheProcedure(){
		AppointmentProcedure proc = new AppointmentProcedure();
		proc.addMaterial(aMaterial(2, 10.0));
		proc.addMaterial(aMaterial(1, 100.0));
		proc.addMedicine(aMedicine(2, 20.0));
		proc.addMedicine(aMedicine(3, 30.0));
		
		double total = 2*10 + 100 + 2*20 + 3*30;
		assertTrue(new Money(total).getAmount().doubleValue() == proc.getTotalAmount().getAmount().doubleValue());
	}

	private AppointmentMaterial aMaterial(int qty, double amount) {
		AppointmentMaterial m = new AppointmentMaterial();
		m.setQty(new Quantity(qty));
		m.setUnitAmount(new Money(amount));
		return m;
	}
	
	private AppointmentMedicine aMedicine(int qty, double amount) {
		AppointmentMedicine m = new AppointmentMedicine();
		m.setQty(new Quantity(qty));
		m.setUnitAmount(new Money(amount));
		return m;
	}

}
