package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.MedicineWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.builders.MedicineWithPriceAndQuantityBuilder;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.MedicineBuilder;
import br.com.easyclinica.tests.helpers.ProcedureBuilder;

public class MedicineDaoTests extends DaoBase {
	private MedicineDao dao;
	private MedicineWithPriceAndQuantityBuilder MedicineWithPriceAndQuantityBuilder;
	
	@Before
	public void setUp() {
		dao = new MedicineDao(session);
	
		MedicineWithPriceAndQuantityBuilder = new MedicineWithPriceAndQuantityBuilder(dao);
	}
		
	@Test
	public void shouldGetMedicinesWithPriceAndQuantity() {
		Procedure procedure = aSavedProcedure();
		HealthCarePlan healthCarePlan = aSavedHealthCarePlan();
		
		Medicine medicine1 = aSavedMedicine();
		procedure.addMedicine(medicine1, new BigDecimal(1));
		Medicine medicine2 = aSavedMedicine();
		procedure.addMedicine(medicine2, new BigDecimal(2));
		session.save(procedure);
		
		List<MedicineWithPriceAndQuantity> Medicines = MedicineWithPriceAndQuantityBuilder.inTheHealthCarePlan(healthCarePlan)
																						  .ofTheProcedure(procedure)
																						  .execute();
		
		assertEquals(2, Medicines.size());
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
