package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.tests.helpers.ClinicBuilder;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;

public class HealthCarePlanDaoTests {
	private HealthCarePlanDao dao;
	private EntityManager em;
	private Clinic clinic;
	
	@Before
	public void setUp() {
		em = Persistence.createEntityManagerFactory("test").createEntityManager();
		em.getTransaction().begin();
		dao = new HealthCarePlanDao(em);
		
		ClinicDao clinicDao = new ClinicDao(em);
		clinic = new ClinicBuilder().withName("EasyClinica").withDomain("easyclinica").instance();
		clinicDao.add(clinic);
	}
	
	@After
	public void tearDown() {
		em.getTransaction().rollback();
		em.close();
	}
	
	@Test
	public void shouldAdd() {
		HealthCarePlan plan = new HealthCarePlanBuilder().ofTheClinic(clinic).instance();
		dao.add(plan);
		
		List<HealthCarePlan> list = dao.get();
		assertEquals(1, list.size());
		
		HealthCarePlan newOne = list.get(0);
		assertEquals(plan.getClinic().getName().toString(), newOne.getClinic().getName().toString());
		assertEquals(plan.getName().toString(), newOne.getName().toString());
		assertEquals(plan.getAddress().getStreet().toString(), newOne.getAddress().getStreet().toString());
		assertEquals(plan.getAddress().getNeighborhood().toString(), newOne.getAddress().getNeighborhood().toString());
		assertEquals(plan.getAddress().getPostalCode().toString(), newOne.getAddress().getPostalCode().toString());
		assertEquals(plan.getAddress().getCity().toString(), newOne.getAddress().getCity().toString());
		assertEquals(plan.getAddress().getState().toString(), newOne.getAddress().getState().toString());
		assertEquals(plan.getTelephone().toString(), newOne.getTelephone().toString());
		assertEquals(plan.getEmail().toString(), newOne.getEmail().toString());
		assertEquals(plan.getWebsite().toString(), newOne.getWebsite().toString());
		assertEquals(plan.getContact().toString(), newOne.getContact().toString());
		assertEquals(plan.getObservations().toString(), newOne.getObservations().toString());
		assertEquals(plan.getCh().getMoney(), newOne.getCh().getMoney(), 0.001);
		assertEquals(plan.getActive().isActive(), newOne.getActive().isActive());
	}

	@Test
	public void shouldUpdate() {
		HealthCarePlan plan = new HealthCarePlanBuilder().ofTheClinic(clinic).instance();
		dao.add(plan);
		
		HealthCarePlan updatedPlan = new HealthCarePlanBuilder()
			.withId(plan.getId())
			.withName("new Amil")
			.ofTheClinic(clinic)
			.instance();
		dao.update(updatedPlan);
		
		HealthCarePlan secondRetrievedPlan = dao.getById(plan.getId());
		assertNotNull(secondRetrievedPlan);
		assertEquals("new Amil", secondRetrievedPlan.getName().toString());
	}
	
	@Test
	public void shouldGetById() {
		HealthCarePlan plan = new HealthCarePlanBuilder().ofTheClinic(clinic).instance();
		dao.add(plan);
		
		HealthCarePlan retrievedPlan = dao.getById(plan.getId());
		
		assertNotNull(retrievedPlan);
		assertEquals(plan.getId(), retrievedPlan.getId());
	}
	
	@Test
	public void shouldCountElements() {
		HealthCarePlan firstPlan = new HealthCarePlanBuilder().ofTheClinic(clinic).instance();
		HealthCarePlan secondPlan = new HealthCarePlanBuilder().ofTheClinic(clinic).instance();
		
		dao.add(firstPlan);
		dao.add(secondPlan);
		
		assertEquals(2, dao.count());
	}
	
	@Test
	public void shouldPaginate() {
		HealthCarePlan firstPlan = new HealthCarePlanBuilder().ofTheClinic(clinic).withName("a").instance();
		HealthCarePlan secondPlan = new HealthCarePlanBuilder().ofTheClinic(clinic).withName("b").instance();
		
		dao.add(firstPlan);
		dao.add(secondPlan);
		
		assertEquals(firstPlan.getName().toString(), dao.get(0, 1).get(0).getName().toString());
	}
}
