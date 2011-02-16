package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Quantity;

public class AppointmentMaterialTests {

	@Test
	public void shouldCalculateTotalAmount() {
		AppointmentMaterial am = new AppointmentMaterial();
		am.setQty(new Quantity(2.0F));
		am.setUnitAmount(new Money(10.0));
		
		assertEquals(new Money(20.0), am.getTotalAmount());
	}
}
