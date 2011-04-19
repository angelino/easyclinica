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
	private final ValidatorUtils validatorUtils;
	
	public DefaultEmployeeValidator(AllEmployees employees, ValidatorUtils validatorUtils) {
		this.employees = employees;
		this.validatorUtils = validatorUtils;
	}

	public List<Error> validate(Employee obj) {
		List<Error> errors = new ArrayList<Error>();
		if(validatorUtils.isNullOrEmpty(obj.getName())) {
			errors.add(new Error("employee", ValidationMessages.INVALID_NAME));
		}
		if(validatorUtils.isNullOrEmpty(obj.getLogin())) {
			errors.add(new Error("employee", ValidationMessages.INVALID_LOGIN));
		}
		if(employees.getByLogin(obj.getLogin())!=null) {
			errors.add(new Error("employee", ValidationMessages.LOGIN_ALREADY_EXISTS));
		}
		return errors;
	}

	public List<Error> validateProfileUpdate(Employee employee) {
		List<Error> errors = new ArrayList<Error>();
		if(validatorUtils.isNullOrEmpty(employee.getName())) {
			errors.add(new Error("employee", ValidationMessages.INVALID_NAME));
		}
		return errors;
	}
}
