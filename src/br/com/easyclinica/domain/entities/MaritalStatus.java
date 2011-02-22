package br.com.easyclinica.domain.entities;

public enum MaritalStatus {
	SINGLE("Solteiro"),
	MARRIED("Casado"),
	DIVORCED("Divorciado"),
	UNMARRIED_PARTNER("Parceiro não casado"),
	WIDOWED("Viúvo");
	
	private String formattedName;
	MaritalStatus(String formattedName) {
		this.formattedName = formattedName;
	}

	public String getFormattedName() {
		return this.formattedName;
	}
}
