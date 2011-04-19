package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Medicine;

public class MedicineBuilder {
	private Medicine medicine;
	
	public MedicineBuilder() {
		this(0);
	}
	
	public MedicineBuilder(int id) {
		medicine = new Medicine(id);
		medicine.setName("Medicine");
	}
	
	public MedicineBuilder withName(String name) {
		medicine.setName(name);
		return this;
	}
	
	public Medicine instance() {
		return medicine;
	}
}
