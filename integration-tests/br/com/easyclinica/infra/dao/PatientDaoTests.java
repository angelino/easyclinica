package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.PatientBuilder;

public class PatientDaoTests extends BaseIntegrationTests {
	private PatientDao dao;
	private HealthCarePlan plan;
	
	@Before
	public void setUp() {
		dao = new PatientDao(em);
		
		plan = new HealthCarePlanBuilder().ofTheClinic(clinic).instance();
		em.persist(plan);
	}

	@Test
	public void shouldCountElements() {
		Patient firstPatient = new PatientBuilder().ofTheClinic(clinic).withHealthCarePlan(plan).instance();
		Patient secondPatient = new PatientBuilder().ofTheClinic(clinic).withHealthCarePlan(plan).instance();
		
		dao.add(firstPatient);
		dao.add(secondPatient);
		
		assertEquals(2, dao.count());
	}
	
	@Test
	public void shouldPaginate() {
		Patient firstPatient = new PatientBuilder().ofTheClinic(clinic).withName("a").withHealthCarePlan(plan).instance();
		Patient secondPatient = new PatientBuilder().ofTheClinic(clinic).withName("b").withHealthCarePlan(plan).instance();
		
		dao.add(firstPatient);
		dao.add(secondPatient);
		
		assertEquals(firstPatient.getName().toString(), dao.get(0, 1).get(0).getName().toString());
	}
	
	@Test
	public void shouldAdd() {
		Patient patient = new PatientBuilder().ofTheClinic(clinic).withHealthCarePlan(plan).instance();
		dao.add(patient);
		
		List<Patient> list = dao.get(0, 1);
		assertEquals(1, list.size());
		
		Patient newOne = list.get(0);
		assertEquals(patient.getClinic().getName().toString(), newOne.getClinic().getName().toString());
		assertEquals(patient.getName().toString(), newOne.getName().toString());
		assertEquals(patient.getAddress().getStreet().toString(), newOne.getAddress().getStreet().toString());
		assertEquals(patient.getAddress().getNeighborhood().toString(), newOne.getAddress().getNeighborhood().toString());
		assertEquals(patient.getAddress().getPostalCode().toString(), newOne.getAddress().getPostalCode().toString());
		assertEquals(patient.getAddress().getCity().toString(), newOne.getAddress().getCity().toString());
		assertEquals(patient.getAddress().getState().toString(), newOne.getAddress().getState().toString());
		assertEquals(patient.getTelephone().toString(), newOne.getTelephone().toString());
		assertEquals(patient.getCellphone().toString(), newOne.getCellphone().toString());
		assertEquals(patient.getEmail().toString(), newOne.getEmail().toString());
		assertEquals(patient.getObservations().toString(), newOne.getObservations().toString());
		assertNotNull(newOne.getHealthCarePlan());
		assertEquals(patient.getHealthCarePlan().getName().toString(),newOne.getHealthCarePlan().getName().toString());
	}

	@Test
	public void shouldUpdate() {
		Patient patient = new PatientBuilder().ofTheClinic(clinic).withHealthCarePlan(plan).instance();
		dao.add(patient);
		
		Patient updatedPatient = new PatientBuilder(patient.getId())
			.ofTheClinic(clinic)
			.withName("new Patient")
			.withHealthCarePlan(plan)
			.instance();
		dao.update(updatedPatient);
		
		Patient secondRetrievedPatient = dao.getById(patient.getId());
		assertNotNull(secondRetrievedPatient);
		assertEquals("new Patient", secondRetrievedPatient.getName().toString());
	}
	
	@Test
	public void shouldGetById() {
		Patient patient = new PatientBuilder().ofTheClinic(clinic).withHealthCarePlan(plan).instance();
		dao.add(patient);
		
		Patient retrievedPatient = dao.getById(patient.getId());
		
		assertNotNull(retrievedPatient);
		assertEquals(patient.getId(), retrievedPatient.getId());
	}
}
