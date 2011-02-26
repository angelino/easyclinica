package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Procedure;

public class ProcedureBuilder {

	private Procedure instance;

	public ProcedureBuilder() {
		this(0);
	}
	
	public ProcedureBuilder(int id) {
		instance = new Procedure(id);
		instance.setAmbCode("AMB");
		instance.setCh(10);
		instance.setName("Procedure");
		instance.setTussCode("TUSS");
	}
	
	public Procedure instance() {
		return instance;
	}

	public ProcedureBuilder withName(String name) {
		instance.setName(name);
		return this;
	}
}
