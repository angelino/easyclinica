package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.Clinic;

public interface AllClinics extends Pagging<Clinic> {
	List<Clinic> get();
	Clinic getById(int id);
	
	void add(Clinic clinic);
	void update(Clinic clinic);
}
