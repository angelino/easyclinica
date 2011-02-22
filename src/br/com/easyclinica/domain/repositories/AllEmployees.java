package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.Employee;

public interface AllEmployees {
	Employee getByLogin(String login);
	List<Employee> get();
	void add(Employee employee);
	Employee getById(int id);
	void update(Employee employee);
}
