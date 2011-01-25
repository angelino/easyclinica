package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Clinic;

public class ClinicBuilder {

	private Clinic clinic;
	
	public ClinicBuilder() {
		clinic = new Clinic();
	}
	
	public ClinicBuilder(int id) {
		clinic = new Clinic(id);
	}
	
	public ClinicBuilder withName(String name)
	{
		clinic.setName(name);
		
		return this;
	}
	
	public ClinicBuilder withDomain(String domain)
	{
		clinic.setDomain(domain);
		
		return this;
	}
	
	public Clinic instance() {
		return clinic;
	}
}
