package br.com.easyclinica.domain.auth;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.actions.AppointmentsController;
import br.com.easyclinica.domain.auth.AppointmentsNotToDoctors;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;
import br.com.easyclinica.infra.multitenancy.LoggedUser;
import br.com.easyclinica.tests.helpers.ClinicBuilder;
import br.com.easyclinica.tests.helpers.EmployeeBuilder;

public class AppointmentsNotToDoctorsTests {

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
		boolean accepts = new AppointmentsNotToDoctors().allows(AppointmentsController.class, employee);
		
		assertFalse(accepts);
	}

	@Test
	public void shouldAllowAttendants() {
		Employee employee = as(Position.ATTENDANT);
		
		loggedUser.set(clinic, employee);
		boolean accepts = new AppointmentsNotToDoctors().allows(AppointmentsController.class, employee);
		
		assertTrue(accepts);
	}
	
	@Test
	public void shouldAllowOwners() {
		Employee employee = as(Position.OWNER);
		
		loggedUser.set(clinic, employee);
		boolean accepts = new AppointmentsNotToDoctors().allows(AppointmentsController.class, employee);
		
		assertTrue(accepts);
	}

	@Test
	public void shouldAllowFinancial() {
		Employee employee = as(Position.FINANCIAL);
		
		loggedUser.set(clinic, employee);
		boolean accepts = new AppointmentsNotToDoctors().allows(AppointmentsController.class, employee);
		
		assertTrue(accepts);
	}

	public Employee as(Position position) {
		return new EmployeeBuilder().withPosition(position).instance();
	}
}
