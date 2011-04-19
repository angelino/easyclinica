package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.Patient;

public interface AllPatients extends Pagging<Patient> {

	void add(Patient patient);
	Patient getById(int id);
	void update(Patient patient);
	
	List<Patient> getAll();
	List<Patient> search(String text);
}
