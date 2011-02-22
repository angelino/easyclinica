package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;

public class EmployeeBuilder {

	private Employee instance;
	
	public EmployeeBuilder() {
		instance = new Employee();
		instance.setName("John Doe");
		instance.setLogin("johndoe");
		instance.active();
	}
	
	public Employee instance() {
		return instance;
	}

	public EmployeeBuilder withPosition(Position position) {
		instance.setPosition(position);
		return this;
	}

	public EmployeeBuilder notActive() {
		instance.deactive();
		return this;
	}

	public EmployeeBuilder withName(String name) {
		instance.setName(name);
		return this;
	}
	
	public EmployeeBuilder withLogin(String login) {
		instance.setLogin(login);
		return this;
	}
}
