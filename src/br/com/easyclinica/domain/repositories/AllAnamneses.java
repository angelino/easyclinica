package br.com.easyclinica.domain.repositories;

import br.com.easyclinica.domain.entities.Anamnese;

public interface AllAnamneses {
	void add(Anamnese anamnese);
	Anamnese getById(int id);
	void update(Anamnese anamnese);
	void delete(Anamnese anamnese);
}
