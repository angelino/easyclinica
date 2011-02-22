package br.com.easyclinica.domain.validators.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.repositories.AllEmployees;
import br.com.easyclinica.domain.validators.EmployeeValidator;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.domain.validators.ValidatorUtils;

@Component
public class DefaultEmployeeValidator implements EmployeeValidator {
	
	private final AllEmployees employees;

	public DefaultEmployeeValidator(AllEmployees employees) {
		this.employees = employees;
	}

	public List<Error> validate(Employee obj) {
		List<Error> errors = new ArrayList<Error>();
		if(ValidatorUtils.isNullOrEmpty(obj.getName())) {
			errors.add(new Error("employee", ValidationMessages.INVALID_NAME));
		}
		if(ValidatorUtils.isNullOrEmpty(obj.getLogin())) {
			errors.add(new Error("employee", ValidationMessages.INVALID_LOGIN));
		}
		if(employees.getByLogin(obj.getLogin())!=null) {
			errors.add(new Error("employee", ValidationMessages.LOGIN_ALREADY_EXISTS));
		}
		return errors;
	}
}
