package br.com.easyclinica.domain.entities;

public enum Gender {
	M("Masculino"),
	F("Feminino");

	private String formattedName;

	Gender(String formattedName) {
		this.formattedName = formattedName;
	}

	public String getFormattedName() {
		return this.formattedName;
	}
}
