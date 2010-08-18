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
				0,
				new Name("Doutor"),
				new CRM("55.555"),
				new Specialty("pediatra"),
				new Telephone("55 11 9999-9999"),
				new Email("doutor@easyclinica.com.br"),
				new Observations("")
		);
	}
	
	public DoctorBuilder withName(String name)
	{
		doctor = new Doctor(
				doctor.getId(),
				new Name(name),
				doctor.getCrm(),
				doctor.getSpecialty(),
				doctor.getTelephone(),
				doctor.getEmail(),
				doctor.getObservations()
		);
		
		return this;
	}
	
	public DoctorBuilder withCrm(String crm)
	{
		doctor = new Doctor(
				doctor.getId(),
				doctor.getName(),
				new CRM(crm),
				doctor.getSpecialty(),
				doctor.getTelephone(),
				doctor.getEmail(),
				doctor.getObservations()
		);
		
		return this;
	}
	
	public DoctorBuilder withId(int id)
	{
		doctor = new Doctor(
				id,
				doctor.getName(),
				doctor.getCrm(),
				doctor.getSpecialty(),
				doctor.getTelephone(),
				doctor.getEmail(),
				doctor.getObservations()
		);
		
		return this;
	}
	
	public Doctor instance() {
		return doctor;
	}
}
