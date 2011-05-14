package br.com.easyclinica.domain.repositories;

import br.com.easyclinica.domain.entities.GeneralObservations;

public interface AllGeneralObservations {
	void add(GeneralObservations generalObservations);
	GeneralObservations getById(int id);
	void update(GeneralObservations generalObservations);
	void delete(GeneralObservations generalObservations);
}
