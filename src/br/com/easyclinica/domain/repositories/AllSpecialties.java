package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.Specialty;

public interface AllSpecialties extends Pagging<Specialty> {
	Specialty getById(int id);
	List<Specialty> getAll();
}
