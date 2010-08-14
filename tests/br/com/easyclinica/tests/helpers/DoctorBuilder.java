package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.types.CRM;
import br.com.easyclinica.domain.types.Email;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Observations;
import br.com.easyclinica.domain.types.Specialty;
import br.com.easyclinica.domain.types.Telephone;

public class DoctorBuilder {
	private Doctor doctor;
	
	public DoctorBuilder() {
		doctor = new Doctor(
				new Name("Doutor"),
				new CRM("55.555"),
				new Specialty("pediatra"),
				new Telephone("55 11 9999-9999"),
				new Email("doutor@easyclinica.com.br"),
				new Observations("")
		);
	}
	
	public DoctorBuilder withNameAndCRM(String name, String crm)
	{
		doctor = new Doctor(
				new Name(name),
				new CRM(crm),
				new Specialty("pediatra"),
				new Telephone("55 11 9999-9999"),
				new Email("doutor@easyclinica.com.br"),
				new Observations("")
		);
		
		return this;
	}
	
	public Doctor instance() {
		return doctor;
	}
}
