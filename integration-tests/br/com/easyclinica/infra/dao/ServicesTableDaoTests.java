package br.com.easyclinica.infra.dao;

import java.util.List;

import static org.junit.Assert.*;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.ServicesTable;
import br.com.easyclinica.domain.types.Name;

public class ServicesTableDaoTests {
	private Session session;
	private ServicesTableDao dao;

	@Before
	public void setUp() {
		session = new AnnotationConfiguration().configure("test-hibernate.cfg.xml").buildSessionFactory().openSession();
		session.beginTransaction();
		dao = new ServicesTableDao(session);
	}
	
	@After
	public void tearDown() {
		session.getTransaction().rollback();
		session.close();
	}

	@Test
	public void shouldGetAllTables() {
		ServicesTable table1 = new ServicesTable(new Name("table 1"));
		ServicesTable table2 = new ServicesTable(new Name("table 2"));
		session.persist(table1);
		session.persist(table2);
		
		List<ServicesTable> tables = dao.get();
		
		assertEquals(2, tables.size());
	}
}
