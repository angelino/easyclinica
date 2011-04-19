package br.com.easyclinica.infra.multitenancy;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Employee;

@Component
@SessionScoped
public class LoggedUser implements Serializable {

	private static final long serialVersionUID = 1L;
	private Employee employee;
	private Clinic clinic;
	
	public void set(Clinic clinic, Employee employee) {
		this.employee = employee;
		this.clinic = clinic;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public Clinic getClinic() {
		return clinic;
	}

	public boolean isLogged() {
		return employee != null && clinic != null;
	}

	public void logoff() {
		employee = null;
		clinic = null;
	}

	public boolean isDoctor() {
		return isLogged() && employee.getDoctor() != null;
	}

	public Doctor getDoctor() {
		return employee!=null ? employee.getDoctor() : null;
	}
	
	
	
	
}
