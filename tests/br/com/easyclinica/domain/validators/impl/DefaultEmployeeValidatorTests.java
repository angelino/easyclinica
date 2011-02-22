package br.com.easyclinica.domain.validators.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.repositories.AllEmployees;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.tests.helpers.EmployeeBuilder;
import static org.mockito.Mockito.*;
public class DefaultEmployeeValidatorTests {

	private DefaultEmployeeValidator validator;
	private AllEmployees employees;

	@Before
	public void setUp() {
		employees = mock(AllEmployees.class);
		validator = new DefaultEmployeeValidator(employees);
		
		when(employees.getByLogin(any(String.class))).thenReturn(null);
	}
	
	@Test
	public void shouldNotReturnErrorsOnAValidEmployee() {
		Employee employee = new EmployeeBuilder().instance();
		
		List<Error> errors = validator.validate(employee);		
		assertEquals(0, errors.size());
	
	}
	
	@Test
	public void shouldReturnErrorIfNameIsNotPresent() {
		Employee employee = new EmployeeBuilder().withName("").instance();
		
		List<Error> errors = validator.validate(employee);		
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_NAME, errors.get(0).getKey());
	
	}
	
	@Test
	public void shouldReturnErrorIfLoginIsNotPresent() {
		Employee employee = new EmployeeBuilder().withLogin("").instance();
		
		List<Error> errors = validator.validate(employee);		
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_LOGIN, errors.get(0).getKey());
	}
	
	@Test
	public void shouldReturnErrorIfLoginAlreadyExists() {
		Employee employee = new EmployeeBuilder().withLogin("johnny").instance();
		Employee johnny = new EmployeeBuilder().withLogin("johnny").instance();
		
		when(employees.getByLogin("johnny")).thenReturn(johnny);
		
		List<Error> errors = validator.validate(employee);
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.LOGIN_ALREADY_EXISTS, errors.get(0).getKey());		
	}

}
