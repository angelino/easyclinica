package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.types.Address;
import br.com.easyclinica.domain.types.City;
import br.com.easyclinica.domain.types.Email;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Neighborhood;
import br.com.easyclinica.domain.types.Observations;
import br.com.easyclinica.domain.types.PostalCode;
import br.com.easyclinica.domain.types.State;
import br.com.easyclinica.domain.types.Street;
import br.com.easyclinica.domain.types.Telephone;
import br.com.easyclinica.domain.types.Website;

public class HealthCarePlanDaoTests {

	private Session session;
	private HealthCarePlanDao dao;

	@Before
	public void setUp() {
		session = new AnnotationConfiguration().configure("test-hibernate.cfg.xml").buildSessionFactory().openSession();
		session.beginTransaction();
		dao = new HealthCarePlanDao(session);
	}
	
	@After
	public void tearDown() {
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	public void shouldAdd() {
		dao.add(aHealthCarePlan());
		
		List<HealthCarePlan> list = dao.get();
		assertEquals(1, list.size());
		
		HealthCarePlan newOne = list.get(0);
		assertEquals("some healthcare", newOne.getName().toString());
		assertEquals("street", newOne.getAddress().getStreet().toString());
		assertEquals("nhood", newOne.getAddress().getNeighborhood().toString());
		assertEquals("123", newOne.getAddress().getPostalCode().toString());
		assertEquals("city", newOne.getAddress().getCity().toString());
		assertEquals("ST", newOne.getAddress().getState().toString());
		assertEquals("11 223344", newOne.getTelephone().toString());
		assertEquals("email@email.com", newOne.getEmail().toString());
		assertEquals("website.com", newOne.getWebsite().toString());
		assertEquals("contact", newOne.getContact().toString());
		assertEquals("some obs", newOne.getObservations().toString());
	}

	@Test
	public void shouldUpdate() {
		HealthCarePlan plan = aHealthCarePlan();
		dao.add(plan);
		
		HealthCarePlan updatedPlan = aHealthCarePlan(plan.getId(), "new Amil");
		dao.update(updatedPlan);
		
		HealthCarePlan secondRetrievedPlan = dao.getById(plan.getId());
		assertNotNull(secondRetrievedPlan);
		assertEquals("new Amil", secondRetrievedPlan.getName().toString());
	}
	
	@Test
	public void shouldGetById() {
		HealthCarePlan plan = aHealthCarePlan();
		dao.add(plan);
		
		HealthCarePlan retrievedPlan = dao.getById(plan.getId());
		
		assertNotNull(retrievedPlan);
		assertEquals(plan.getId(), retrievedPlan.getId());
	}
	
	private HealthCarePlan aHealthCarePlan() {
		return aHealthCarePlan(0, "some healthcare");
	}
	
	private HealthCarePlan aHealthCarePlan(int id, String name) {
		return new HealthCarePlan(
				id,
				new Name(name),
				new Address(new Street("street"), new Neighborhood("nhood"), new PostalCode("123"), new City("city"), new State("ST")),
				new Telephone("11 223344"),
				new Email("email@email.com"),
				new Website("website.com"),
				new Name("contact"),
				new Observations("some obs")
		);
	}
}
