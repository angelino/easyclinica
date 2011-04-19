package br.com.easyclinica.domain.auth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.actions.ClinicController;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;
import br.com.easyclinica.infra.multitenancy.LoggedUser;
import br.com.easyclinica.tests.helpers.ClinicBuilder;
import br.com.easyclinica.tests.helpers.EmployeeBuilder;

public class ClinicInfoOnlyToOwnersTests {

	private LoggedUser loggedUser;
	private Clinic clinic;
	
	@Before
	public void setUp() {
		clinic = new ClinicBuilder().instance();
		loggedUser = new LoggedUser();
	}
	@Test
	public void shouldNotAllowDoctors() {
		Employee employee = as(Position.DOCTOR);
		
		loggedUser.set(clinic, employee);
		boolean accepts = new ClinicInfoOnlyToOwners().allows(ClinicController.class, employee);
		
		assertFalse(accepts);
	}

	@Test
	public void shouldNotAllowAttendants() {
		Employee employee = as(Position.ATTENDANT);
		
		loggedUser.set(clinic, employee);
		boolean accepts = new ClinicInfoOnlyToOwners().allows(ClinicController.class, employee);
		
		assertFalse(accepts);
	}

	@Test
	public void shouldNotAllowFinancial() {
		Employee employee = as(Position.FINANCIAL);
		
		loggedUser.set(clinic, employee);
		boolean accepts = new ClinicInfoOnlyToOwners().allows(ClinicController.class, employee);
		
		assertFalse(accepts);
	}

	@Test
	public void shouldAllowOwners() {
		Employee employee = as(Position.OWNER);
		
		loggedUser.set(clinic, employee);
		boolean accepts = new ClinicInfoOnlyToOwners().allows(ClinicController.class, employee);
		
		assertTrue(accepts);
	}
	
	public Employee as(Position position) {
		return new EmployeeBuilder().withPosition(position).instance();
	}
}
