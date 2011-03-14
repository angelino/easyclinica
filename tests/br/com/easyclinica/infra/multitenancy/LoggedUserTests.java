package br.com.easyclinica.infra.multitenancy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.tests.helpers.ClinicBuilder;
import br.com.easyclinica.tests.helpers.DoctorBuilder;
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
	
	@Test
	public void shouldLogOff() {
		loggedUser.set(clinic, employee);
		assertTrue(loggedUser.isLogged());
		
		loggedUser.logoff();
		
		assertFalse(loggedUser.isLogged());
	}
	
	@Test
	public void shouldIdentifyIfItIsADoctor() {
		assertFalse(loggedUser.isDoctor());
		
		loggedUser.set(clinic, employee);
		assertFalse(loggedUser.isDoctor());
		
		employee.setDoctor(new DoctorBuilder().instance());
		assertTrue(loggedUser.isDoctor());
	}
	
	@Test
	public void shouldGetDoctor() {
		assertNull(loggedUser.getDoctor());
		
		loggedUser.set(clinic, employee);
		assertNull(loggedUser.getDoctor());
		
		Doctor doctor = new DoctorBuilder().instance();
		employee.setDoctor(doctor);
		assertSame(doctor, loggedUser.getDoctor());		
	}
}
