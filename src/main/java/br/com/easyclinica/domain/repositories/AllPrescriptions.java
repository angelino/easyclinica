package br.com.easyclinica.domain.repositories;

import br.com.easyclinica.domain.entities.Prescription;

public interface AllPrescriptions {
	void add(Prescription prescription);
	Prescription getById(int id);
	void update(Prescription prescription);
	void delete(Prescription prescription);
}
