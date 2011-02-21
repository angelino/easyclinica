package br.com.easyclinica.domain.entities;

public enum MaritalStatus {
	SINGLE("Solteiro"),
	MARRIED("Casado"),
	DIVORCED("Divorciado"),
	UNMARRIED_PARTNER("Parceiro não casado"),
	WIDOWED("Viúvo");
	
	private String description;
	MaritalStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
