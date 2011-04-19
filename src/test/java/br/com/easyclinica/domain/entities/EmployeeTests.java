package br.com.easyclinica.domain.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.easyclinica.tests.helpers.EmployeeBuilder;


public class EmployeeTests {

	@Test
	public void shouldActivate() {
		Employee e = new EmployeeBuilder().notActive().instance();
		
		assertFalse(e.isActive());
		
		e.active();
		
		assertTrue(e.isActive());
	}
	
	@Test
	public void shouldDeactivate() {
		Employee e = new EmployeeBuilder().instance();
		
		assertTrue(e.isActive());
		
		e.deactive();
		
		assertFalse(e.isActive());
	}
}
