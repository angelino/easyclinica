package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Specialty;

public class SpecialtyBuilder {

	private Specialty instance;
	
	public SpecialtyBuilder() {
		this(0);
	}
	
	public SpecialtyBuilder(int id) {
		instance = new Specialty(id);
		instance.setName("pediatra");
	}

	public Specialty instance() {
		return instance;
	}

	public SpecialtyBuilder withName(String name) {
		instance.setName(name);
		return this;
	}
}
