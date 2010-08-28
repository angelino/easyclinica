package br.com.easyclinica.domain.repositories;

import br.com.easyclinica.domain.entities.Patient;

public interface AllPatients extends Pagging<Patient> {

	void add(Patient patient);
	Patient getById(int id);
	void update(Patient patient);

}
