package br.com.easyclinica.infra.multitenancy;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Employee;

@Component
@SessionScoped
public class Tenant {

	private static Logger logger = Logger.getLogger(Tenant.class);
	
	private String tempDomain;
	private Clinic clinic;
	private Employee employee;

	public Tenant() {
	}
	
	public void setTempDomain(String domain) {
		if(!isLogged()) {
			this.tempDomain = domain;
		}
	}
	
	public String getDomain() {
		if(!isLogged()) return tempDomain;
		return clinic.getDomain();
	}

	public Clinic getClinic() {
		return clinic;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void set(Clinic clinic, Employee employee) {
		this.clinic = clinic;
		this.employee = employee;
		
		logger.info("Logged user: " + employee.getUser() + "("+ clinic.getDomain() +")");
	}

	public boolean isLogged() {
		return clinic != null && employee != null;
	}
	
	
	
}
