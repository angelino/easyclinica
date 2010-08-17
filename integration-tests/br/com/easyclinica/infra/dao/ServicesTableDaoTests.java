package br.com.easyclinica.infra.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.ServicesTable;
import br.com.easyclinica.domain.types.Name;

public class ServicesTableDaoTests {
	private ServicesTableDao dao;
	private EntityManager em;

	@Before
	public void setUp() {
		em = Persistence.createEntityManagerFactory("test").createEntityManager();
		em.getTransaction().begin();
		dao = new ServicesTableDao(em);
	}
	
	@After
	public void tearDown() {
		em.getTransaction().rollback();
		em.close();
	}

	@Test
	public void shouldGetAllTables() {
		ServicesTable table1 = new ServicesTable(new Name("table 1"));
		ServicesTable table2 = new ServicesTable(new Name("table 2"));
		em.persist(table1);
		em.persist(table2);
		
		List<ServicesTable> tables = dao.get();
		
		assertEquals(2, tables.size());
	}
}
