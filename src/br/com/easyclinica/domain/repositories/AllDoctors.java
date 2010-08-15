package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.Doctor;

public interface AllDoctors {
	List<Doctor> get();
	Doctor getById(int id);
	
	void add(Doctor doctor);
	void update(Doctor doctor);
}
