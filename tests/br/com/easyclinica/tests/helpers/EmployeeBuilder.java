package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Employee;

public class EmployeeBuilder {

	private Employee instance;
	
	public EmployeeBuilder() {
		instance = new Employee();
	}
	
	public Employee instance() {
		return instance;
	}
}
