package br.com.easyclinica.domain.entities.builders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.MedicineWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Quantity;
import br.com.easyclinica.infra.dao.BaseIntegrationTests;
import br.com.easyclinica.infra.dao.MedicineDao;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.MedicineBuilder;
import br.com.easyclinica.tests.helpers.ProcedureBuilder;

public class MedicineWithPriceAndQuantityBuilderTests extends BaseIntegrationTests {
	private MedicineDao dao;
	
	@Before
	public void setUp() {
		dao = new MedicineDao(session);
	}
	
	@Test
	public void shouldGetQuantityAndPriceOfProceduresMaterials() {
		Procedure procedure = aSavedProcedure();
		HealthCarePlan healthCarePlan = aSavedHealthCarePlan();
		
		Medicine medicine1 = aSavedMedicine();
		procedure.addMedicine(medicine1, new Quantity(1));
		Medicine medicine2 = aSavedMedicine();
		procedure.addMedicine(medicine2, new Quantity(2));
		session.save(procedure);
		
		PrecifiedMedicine precifiedMedicine1 = new PrecifiedMedicine();
		precifiedMedicine1.setAmount(new Money(10.20));
		precifiedMedicine1.setHealthCarePlan(healthCarePlan);
		precifiedMedicine1.setMedicine(medicine1);
		session.save(precifiedMedicine1);
		
		PrecifiedMedicine precifiedMedicine2 = new PrecifiedMedicine();
		precifiedMedicine2.setAmount(new Money(10.30));
		precifiedMedicine2.setHealthCarePlan(healthCarePlan);
		precifiedMedicine2.setMedicine(medicine2);
		session.save(precifiedMedicine2);
		
		List<MedicineWithPriceAndQuantity> medicine = dao.getMedicinesWithPriceAndQuantity(procedure, healthCarePlan);
		
		assertEquals(medicine.size(), 2);
		assertTrue(medicine.get(0).getAmount().getAmount().doubleValue() == precifiedMedicine1.getAmount().getAmount().doubleValue());
		assertTrue(medicine.get(1).getAmount().getAmount().doubleValue() == precifiedMedicine2.getAmount().getAmount().doubleValue());
		assertTrue(medicine.get(0).getQty().getQty().doubleValue() == procedure.getMedicines().get(0).getQty().getQty().doubleValue());
		assertTrue(medicine.get(1).getQty().getQty().doubleValue() == procedure.getMedicines().get(1).getQty().getQty().doubleValue());
	}
	
	private Procedure aSavedProcedure() {
		Procedure procedure = new ProcedureBuilder().instance();
		session.save(procedure);
		return procedure;
	}
	
	private HealthCarePlan aSavedHealthCarePlan() {
		HealthCarePlan plan = new HealthCarePlanBuilder().withName("bla").withCh(10).instance();
		session.save(plan);
		return plan;
	}
	
	private Medicine aSavedMedicine() {
		Medicine medicine = new MedicineBuilder().instance();
		session.save(medicine);
		return medicine;
	}
}
