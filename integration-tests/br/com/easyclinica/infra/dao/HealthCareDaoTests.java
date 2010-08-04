package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import br.com.easyclinica.domain.entities.HealthCare;
import br.com.easyclinica.domain.types.Name;

public class HealthCareDaoTests {

	private Session session;
	private HealthCareDao dao;

	@Before
	public void setUp() {
		session = new AnnotationConfiguration().configure().buildSessionFactory().openSession();
		session.beginTransaction();
		dao = new HealthCareDao(session);
	}
	
	@After
	public void tearDown() {
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	public void shouldAdd() {
		dao.add(new HealthCare(new Name("some healthcare")));
		
		assertEquals(1, dao.get().size());
		
	}
}
