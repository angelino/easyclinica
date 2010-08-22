package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.ServicesTable;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.PatientBuilder;

public class PatientDaoTests {
	private PatientDao dao;
	private EntityManager em;
	private HealthCarePlan plan;
	
	@Before
	public void setUp() {
		em = Persistence.createEntityManagerFactory("test").createEntityManager();
		em.getTransaction().begin();
		dao = new PatientDao(em);
		
		ServicesTable servicesTable = new ServicesTable(new Name("table"));
		em.persist(servicesTable);
		plan = new HealthCarePlanBuilder().withTable(servicesTable).instance();
		em.persist(plan);
	}
	
	@After
	public void tearDown() {
		em.getTransaction().rollback();
		em.close();
	}

	@Test
	public void shouldCountElements() {
		Patient firstPatient = new PatientBuilder().withHealthCarePlan(plan).instance();
		Patient secondPatient = new PatientBuilder().withHealthCarePlan(plan).instance();
		
		dao.add(firstPatient);
		dao.add(secondPatient);
		
		assertEquals(2, dao.count());
	}
	
	@Test
	public void shouldPaginate() {
		Patient firstPatient = new PatientBuilder().withName("a").withHealthCarePlan(plan).instance();
		Patient secondPatient = new PatientBuilder().withName("b").withHealthCarePlan(plan).instance();
		
		dao.add(firstPatient);
		dao.add(secondPatient);
		
		assertEquals(firstPatient.getName().toString(), dao.get(0, 1).get(0).getName().toString());
	}
	
	@Test
	public void shouldAdd() {
		Patient patient = new PatientBuilder().withHealthCarePlan(plan).instance();
		dao.add(patient);
		
		List<Patient> list = dao.get(0, 1);
		assertEquals(1, list.size());
		
		Patient newOne = list.get(0);
		assertEquals(patient.getName().toString(), newOne.getName().toString());
		assertEquals(patient.getAddress().getStreet().toString(), newOne.getAddress().getStreet().toString());
		assertEquals(patient.getAddress().getNeighborhood().toString(), newOne.getAddress().getNeighborhood().toString());
		assertEquals(patient.getAddress().getPostalCode().toString(), newOne.getAddress().getPostalCode().toString());
		assertEquals(patient.getAddress().getCity().toString(), newOne.getAddress().getCity().toString());
		assertEquals(patient.getAddress().getState().toString(), newOne.getAddress().getState().toString());
		assertEquals(patient.getTelephone().toString(), newOne.getTelephone().toString());
		assertEquals(patient.getCellphone().toString(), newOne.getCellphone().toString());
		assertEquals(patient.getEmail().toString(), newOne.getEmail().toString());
		assertEquals(patient.getHealthCareId().toString(), newOne.getHealthCareId().toString());
		assertEquals(patient.getObservations().toString(), newOne.getObservations().toString());
		assertNotNull(newOne.getHealthCarePlan());
		assertEquals(patient.getHealthCarePlan().getName().toString(),newOne.getHealthCarePlan().getName().toString());
	}

	@Test
	public void shouldUpdate() {
		Patient patient = new PatientBuilder().withHealthCarePlan(plan).instance();
		dao.add(patient);
		
		Patient updatedPatient = new PatientBuilder()
			.withId(patient.getId())
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
		Patient patient = new PatientBuilder().withHealthCarePlan(plan).instance();
		dao.add(patient);
		
		Patient retrievedPatient = dao.getById(patient.getId());
		
		assertNotNull(retrievedPatient);
		assertEquals(patient.getId(), retrievedPatient.getId());
	}
}
