package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;

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
	private ServicesTable servicesTable;
	
	@Before
	public void setUp() {
		em = Persistence.createEntityManagerFactory("test").createEntityManager();
		em.getTransaction().begin();
		dao = new PatientDao(em);
		
		servicesTable = new ServicesTable(new Name("table"));
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
}
