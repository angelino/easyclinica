package br.com.easyclinica.infra.multitenancy;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.infra.web.HttpRequestWrapper;

@Component
@SessionScoped
public class Tenant {

	private String domain;
	private Clinic clinic;
	private final TenantUrlParser urlParser;
	private final HttpRequestWrapper request;
	private Employee employee;

	public Tenant(HttpRequestWrapper request, TenantUrlParser parser) {
		this.request = request;
		this.urlParser = parser;
	}
	
	public String getDomain() {
		if(domain == null && clinic == null) {
			domain = urlParser.parse(request.getUrl());
		}
		return domain;
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
	}
	
	
	
}
