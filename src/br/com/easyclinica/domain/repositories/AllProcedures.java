package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.Procedure;

public interface AllProcedures {

	Procedure getById(int id);
	List<Procedure> search(String text);
}
