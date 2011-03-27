package br.com.easyclinica.domain.entities;

public enum Kinship {
	ME("O Próprio"),
	MOTHER("Mãe"),
	FATHER("Pai"),
	BROTHER("Irmão"),
	SISTER("Irmã");

	private String name;

	Kinship(String name) {
		this.name = name;
	}

	public String getFormattedName() {
		return name;
	}
}
