package br.com.easyclinica.domain.entities;

public enum Kinship {
	ME("kinship.me"),
	MOTHER("kinship.mother"),
	FATHER("kinship.father"),
	BROTHER("kinship.brother"),
	SISTER("kinship.sister");

	private String key;

	Kinship(String key) {
		this.key = key;
	}

	public String getLocaleKey() {
		return key;
	}
}
