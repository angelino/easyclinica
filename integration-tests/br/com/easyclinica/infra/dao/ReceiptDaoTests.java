package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.hibernate.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Kinship;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.Receipt;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.PatientBuilder;
import br.com.easyclinica.tests.helpers.ReceiptBuilder;

public class ReceiptDaoTests  extends DaoBase {
	private ReceiptDao dao;
	private Patient patient;
	private HealthCarePlan plan;
	
	@Before
	public void setUp() {
		dao = new ReceiptDao(session);
		
		plan = new HealthCarePlanBuilder().withName("plan").instance();
		session.save(plan);
		
		patient = new PatientBuilder().withName("patient").withHealthCarePlan(plan).instance();
		session.save(patient);
	}

	@Test(expected=ObjectNotFoundException.class)
	public void shouldDelete() {
		Receipt receipt = aReceipt();
		dao.add(receipt);
		
		Receipt retrievedReceipt = dao.getById(receipt.getId());
		assertNotNull(retrievedReceipt);
		
		dao.delete(retrievedReceipt);
		
		dao.getById(receipt.getId());
	}
	
	@Test
	public void shouldGetById() {
		Receipt receipt = aReceipt();
		dao.add(receipt);
		
		Receipt retrievedReceipt = dao.getById(receipt.getId());
		
		assertNotNull(retrievedReceipt);
		assertEquals(receipt.getId(), retrievedReceipt.getId());
	}
	
	@Test
	public void shouldUpdate() {
		Receipt receipt = aReceipt();
		dao.add(receipt);
		
		Receipt updatedReceipt = new ReceiptBuilder(receipt.getId())
			.withAmount(new BigDecimal(30.00))
			.instance();
		dao.update(updatedReceipt);
		
		Receipt secondRetrievedReceipt = dao.getById(receipt.getId());
		assertNotNull(secondRetrievedReceipt);
		assertEquals(new BigDecimal(30.00), secondRetrievedReceipt.getAmount());
	}

	private Receipt aReceipt() {
		return new ReceiptBuilder().toThePatient(patient)
								   .withAmount(new BigDecimal(20.00))
								   .inNameOf("Fulana")
								   .his(Kinship.MOTHER)
								   .ownerOfTheCpf("346.492.088-74")
								   .instance();
	}
}
