package br.com.easyclinica.infra.multitenancy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.tests.helpers.ClinicBuilder;
import br.com.easyclinica.tests.helpers.EmployeeBuilder;

public class LoggedUserTests {

	private Clinic clinic;
	private Employee employee;
	private LoggedUser loggedUser;

	@Before
	public void setUp() {
		
		loggedUser = new LoggedUser();
		
		clinic = new ClinicBuilder().withDomain("clinicadavila").instance();
		employee = new EmployeeBuilder().instance();
	}
	
	@Test
	public void shouldLogATenant() {
		loggedUser.set(clinic, employee);
		
		assertSame(clinic, loggedUser.getClinic());
		assertSame(employee, loggedUser.getEmployee());
	}
	
	@Test
	public void shouldBeLogged() {
		assertFalse(loggedUser.isLogged());
		
		loggedUser.set(clinic, employee);
		
		assertTrue(loggedUser.isLogged());
	}
}
