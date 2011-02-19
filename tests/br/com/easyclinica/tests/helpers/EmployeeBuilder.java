package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;

public class EmployeeBuilder {

	private Employee instance;
	
	public EmployeeBuilder() {
		instance = new Employee();
	}
	
	public Employee instance() {
		return instance;
	}

	public EmployeeBuilder withPosition(Position position) {
		instance.setPosition(position);
		return this;
	}
}
