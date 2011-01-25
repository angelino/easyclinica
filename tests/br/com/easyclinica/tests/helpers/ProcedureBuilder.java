package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Procedure;

public class ProcedureBuilder {

	private Procedure instance;

	public ProcedureBuilder(int id) {
		instance = new Procedure(id);
	}
	
	public Procedure instance() {
		return instance;
	}
}
