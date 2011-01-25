package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Specialty;

public class DoctorBuilder {
	private Doctor doctor;
	
	public DoctorBuilder() {
		this(0);
	}
	public DoctorBuilder(int id) {
		doctor = new Doctor(id);
		doctor.setName("Doutor");
		doctor.setCrm("55.555");
		doctor.setSpecialty(aSpecialty("pediatra"));
		doctor.setClinic(new ClinicBuilder().instance());
		doctor.setTelephone("55 11 9999-9999");
		doctor.setEmail("doutor@easyclinica.com.br");
		doctor.setObservations("");
	}
	
	private Specialty aSpecialty(String name) {
		Specialty specialty = new Specialty();
		specialty.setName(name);
		return specialty;
	}
	
	public DoctorBuilder withName(String name)
	{
		doctor.setName(name);
		
		return this;
	}
	
	public DoctorBuilder withCrm(String crm)
	{
		doctor.setCrm(crm);
		return this;
	}
	
	public DoctorBuilder ofTheClinic(Clinic clinic)
	{
		doctor.setClinic(clinic);
		return this;
	}
	
	public Doctor instance() {
		return doctor;
	}
	public DoctorBuilder withSpecialty(Specialty specialty) {
		doctor.setSpecialty(specialty);
		return this;
	}
}
