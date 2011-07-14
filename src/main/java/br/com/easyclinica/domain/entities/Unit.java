package br.com.easyclinica.domain.entities;

public enum Unit {
	UN("unidade"),
	FR("frasco"),
	ML("ml"),
	PAR("par"),
	KG("kg"),
	PACOTE("pacote"),
	CENTIMETRO("centímetro"),
	METRO("metro");

	private String name;

	Unit(String name) {
		this.name = name;
	}

	public String getFormattedName() {
		return name;
	}
}
