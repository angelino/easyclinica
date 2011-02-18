package br.com.easyclinica.infra.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.easyclinica.domain.entities.Employee;

public class EmployeeDaoTests extends BaseIntegrationTests {

	@Test
	public void shouldFindEmployeeByLogin() {
		Employee e = new Employee();
		e.setLogin("login");
		
		session.save(e);
		
		Employee retrieved = new EmployeeDao(session).getByLogin("login");
		
		assertNotNull(retrieved);
		assertEquals("login", retrieved.getLogin());
	}
}
