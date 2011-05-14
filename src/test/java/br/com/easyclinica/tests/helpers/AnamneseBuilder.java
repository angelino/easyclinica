package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Anamnese;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Patient;

public class AnamneseBuilder {

	private Anamnese anamnese;

	public AnamneseBuilder() {
		this(0);
	}
	
	public AnamneseBuilder(int id) {
		this.anamnese = new Anamnese(id);
	}

	public AnamneseBuilder withDoctor(Doctor doctor) {
		anamnese.setDoctor(doctor);
		return this;
	}
	
	public AnamneseBuilder withComplaint(String text) {
		anamnese.setComplaintAndDuration(text);
		return this;
	}
	
	public Anamnese instance() {
		return this.anamnese;
	}

	public AnamneseBuilder withPatient(Patient patient) {
		anamnese.setPatient(patient);
		return this;
	}
}
