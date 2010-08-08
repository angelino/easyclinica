package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;

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
		dao.add(new HealthCarePlan(
				new Name("some healthcare"),
				new Address(new Street("street"), new Neighborhood("nhood"), new PostalCode("123"), new City("city"), new State("ST")),
				new Telephone("11 223344"),
				new Email("email@email.com"),
				new Website("website.com"),
				new Name("contact"),
				new Observations("some obs")
		));
		
		List<HealthCarePlan> list = dao.get();
		assertEquals(1, list.size());
		
		HealthCarePlan newOne = list.get(0);
		assertEquals("some healthcare", newOne.getName().get());
		assertEquals("street", newOne.getAddress().getStreet().get());
		assertEquals("nhood", newOne.getAddress().getNeighborhood().get());
		assertEquals("123", newOne.getAddress().getPostalCode().get());
		assertEquals("city", newOne.getAddress().getCity().get());
		assertEquals("ST", newOne.getAddress().getState().get());
		assertEquals("11 223344", newOne.getTelephone().get());
		assertEquals("email@email.com", newOne.getEmail().get());
		assertEquals("website.com", newOne.getWebsite().get());
		assertEquals("contact", newOne.getContact().get());
		assertEquals("some obs", newOne.getObservations().get());
	}
}
