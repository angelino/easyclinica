package br.com.easyclinica.domain.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.repositories.AllEmployees;
import br.com.easyclinica.domain.repositories.ClinicInfo;
import br.com.easyclinica.infra.md5.MD5Calculator;
import br.com.easyclinica.infra.multitenancy.LoggedUser;
import br.com.easyclinica.tests.helpers.ClinicBuilder;

public class AuthenticationTests {

	private LoggedUser loggedUser;
	private AllEmployees employees;
	private ClinicInfo clinicInfo;
	private MD5Calculator md5;

	@Before
	public void setUp() {
		loggedUser = new LoggedUser();
		employees = mock(AllEmployees.class);
		
		clinicInfo = mock(ClinicInfo.class);
		when(clinicInfo.get()).thenReturn(new ClinicBuilder().instance());
		
		md5 = mock(MD5Calculator.class);
	}
	
	@Test
	public void shouldLoginValidUser() {
		
		Employee employee = anEmployee("user", "pwd-encriptado");
		when(employees.getByLogin("login")).thenReturn(employee);
		when(md5.calculate("pwd")).thenReturn("pwd-encriptado");
		
		Authentication auth = new Authentication(loggedUser, employees, clinicInfo, md5);
		boolean authorized = auth.user("login", "pwd");
		
		assertTrue(authorized);
		assertEquals(employee, loggedUser.getEmployee());
	}
	

	@Test
	public void shouldNotLoginAValidUserIfItIsDeactivated() {
		
		Employee employee = anEmployee("user", "pwd-encriptado", false);
		when(employees.getByLogin("login")).thenReturn(employee);
		
		Authentication auth = new Authentication(loggedUser, employees, clinicInfo, md5);
		boolean authorized = auth.user("login", "pwd");
		
		assertFalse(authorized);
	}

	@Test
	public void shouldNotLoginIfPasswordIsInvalid() {
		
		Employee employee = anEmployee("user", "pwd-encriptado");
		when(employees.getByLogin("login")).thenReturn(employee);
		when(md5.calculate("pwd1")).thenReturn("erro");
		
		Authentication auth = new Authentication(loggedUser, employees, clinicInfo, md5);
		boolean authorized = auth.user("login", "pwd1");
		
		assertFalse(authorized);
		assertNull(loggedUser.getEmployee());
	}


	@Test
	public void shouldNotLoginIfUserDoesNotExist() {
		
		when(employees.getByLogin("login")).thenReturn(null);
		
		Authentication auth = new Authentication(loggedUser, employees, clinicInfo, md5);
		boolean authorized = auth.user("login", "pwd");
		
		assertFalse(authorized);
		assertNull(loggedUser.getEmployee());
	}

	private Employee anEmployee(String login, String password) {
		return anEmployee(login, password, true);
	}
	
	private Employee anEmployee(String login, String password, boolean active) {
		Employee e = new Employee();
		e.setLogin(login);
		e.setPassword(password);
		if(active) e.active(); else e.deactive();
		return e;
	}
}
