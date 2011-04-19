package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.Doctor;

public interface AllDoctors extends Pagging<Doctor> {
	List<Doctor> get();
	List<Doctor> getActivated();
	Doctor getById(int id);
	
	void add(Doctor doctor);
	void update(Doctor doctor);
}
