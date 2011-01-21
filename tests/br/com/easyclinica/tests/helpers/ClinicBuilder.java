package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.types.Domain;
import br.com.easyclinica.domain.types.Name;

public class ClinicBuilder {

	private Clinic clinic;
	
	public ClinicBuilder() {
		clinic = new Clinic(
				0,
				new Name("EasyClinica"),
				new Domain("easyclinica")
		);
	}
	
	public ClinicBuilder withName(String name)
	{
		clinic = new Clinic(
				clinic.getId(),
				new Name(name),
				clinic.getDomain()
		);
		
		return this;
	}
	
	public ClinicBuilder withDomain(String domain)
	{
		clinic = new Clinic(
				clinic.getId(),
				clinic.getName(),
				new Domain(domain)
		);
		
		return this;
	}
	
	public ClinicBuilder withId(int id)
	{
		clinic = new Clinic(
				id,
				clinic.getName(),
				clinic.getDomain()
		);
		
		return this;
	}
	
	public Clinic instance() {
		return clinic;
	}
}
