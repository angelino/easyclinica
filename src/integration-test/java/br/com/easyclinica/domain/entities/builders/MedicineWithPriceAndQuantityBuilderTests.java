package br.com.easyclinica.domain.entities.builders;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.MedicineWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.infra.dao.DaoBase;
import br.com.easyclinica.infra.dao.MedicineDao;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.MedicineBuilder;
import br.com.easyclinica.tests.helpers.ProcedureBuilder;

public class MedicineWithPriceAndQuantityBuilderTests extends DaoBase {
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
		procedure.addMedicine(medicine1, new BigDecimal("1.00"));
		Medicine medicine2 = aSavedMedicine();
		procedure.addMedicine(medicine2, new BigDecimal("2.00"));
		session.save(procedure);
		
		PrecifiedMedicine precifiedMedicine1 = new PrecifiedMedicine();
		precifiedMedicine1.setAmount(new BigDecimal("10.20"));
		precifiedMedicine1.setHealthCarePlan(healthCarePlan);
		precifiedMedicine1.setMedicine(medicine1);
		session.save(precifiedMedicine1);
		
		PrecifiedMedicine precifiedMedicine2 = new PrecifiedMedicine();
		precifiedMedicine2.setAmount(new BigDecimal("10.30"));
		precifiedMedicine2.setHealthCarePlan(healthCarePlan);
		precifiedMedicine2.setMedicine(medicine2);
		session.save(precifiedMedicine2);
		
		List<MedicineWithPriceAndQuantity> medicine = dao.getMedicinesWithPriceAndQuantity(procedure, healthCarePlan);
		
		assertEquals(medicine.size(), 2);
		assertEquals(medicine.get(0).getAmount(), precifiedMedicine1.getAmount());
		assertEquals(medicine.get(1).getAmount(), precifiedMedicine2.getAmount());
		assertEquals(medicine.get(0).getQty(), procedure.getMedicines().get(0).getQty());
		assertEquals(medicine.get(1).getQty(), procedure.getMedicines().get(1).getQty());
	}
	
	private Procedure aSavedProcedure() {
		Procedure procedure = new ProcedureBuilder().instance();
		session.save(procedure);
		return procedure;
	}
	
	private HealthCarePlan aSavedHealthCarePlan() {
		HealthCarePlan plan = new HealthCarePlanBuilder().withName("bla").withCh(new BigDecimal(10)).instance();
		session.save(plan);
		return plan;
	}
	
	private Medicine aSavedMedicine() {
		Medicine medicine = new MedicineBuilder().instance();
		session.save(medicine);
		return medicine;
	}
}
