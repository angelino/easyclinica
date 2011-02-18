package br.com.easyclinica.domain.services;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.repositories.AllEmployees;
import br.com.easyclinica.domain.repositories.ClinicInfo;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Component
@RequestScoped
public class Authentication {

	private final AllEmployees employees;
	private final LoggedUser loggedUser;
	private final ClinicInfo clinicInfo;

	public Authentication(LoggedUser loggedUser, AllEmployees employees, ClinicInfo clinicInfo) {
		this.loggedUser = loggedUser;
		this.employees = employees;
		this.clinicInfo = clinicInfo;
	}

	public boolean user(String login, String password) {
		Employee employee = employees.getByLogin(login);
		if(thereIsAn(employee) && pwdMatches(password, employee)) {
			logThe(employee);
			return true;
		}
		return false;
	}

	private boolean pwdMatches(String password, Employee employee) {
		return employee.getPassword().equals(password);
	}

	private boolean thereIsAn(Employee employee) {
		return employee!=null;
	}

	private void logThe(Employee employee) {
		loggedUser.set(clinicInfo.get(), employee);
	}


}
