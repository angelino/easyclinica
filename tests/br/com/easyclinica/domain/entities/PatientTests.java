package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import br.com.easyclinica.tests.helpers.AddressBuilder;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.PatientBuilder;

public class PatientTests {

	@Test
	public void shouldHaveCompleteInformation() {
		Patient sickGuy = new PatientBuilder(1)
			.withName("John Doe")
			.withAddress(new AddressBuilder().instance())
			.withTelephone("1234")
			.withCellphone("4567")
			.withEmail("patient@sick.com")
			.withHealthCarePlan(new HealthCarePlanBuilder().instance())
			.withObservations("some obs")
			.instance();
		
		assertNotNull(sickGuy);
		assertEquals(1, sickGuy.getId());
		assertEquals("John Doe", sickGuy.getName());
		assertEquals(new AddressBuilder().instance(), sickGuy.getAddress());
		assertEquals("1234", sickGuy.getTelephone());
		assertEquals("4567", sickGuy.getCellphone());
		assertEquals("patient@sick.com", sickGuy.getEmail());
		assertEquals(new HealthCarePlanBuilder().instance().getId(), sickGuy.getHealthCarePlan().getId());
		assertEquals("some obs", sickGuy.getObservations());
	}
	
	@Test
	public void shouldHaveAnamneseData() {
		Patient sickGuy = new PatientBuilder(1).instance();
		
		Anamnese anamnese = new Anamnese();
		sickGuy.addAnamnese(anamnese);
		
		assertSame(anamnese, sickGuy.getAnamneses().get(0));
	}
}
