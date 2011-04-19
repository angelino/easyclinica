package br.com.easyclinica.domain.validators;

import java.util.List;

import br.com.easyclinica.domain.entities.Employee;

public interface EmployeeValidator extends EntityValidator<Employee>{

	List<Error> validateProfileUpdate(Employee employee);

}
