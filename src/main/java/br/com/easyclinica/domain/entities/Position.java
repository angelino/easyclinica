package br.com.easyclinica.domain.entities;

public enum Position {

	FINANCIAL ("Financeiro"), 
	DOCTOR ("Médico"), 
	ATTENDANT("Atendente"),
	OWNER("Dono");
	
	private String name;
	
	Position(String name) {
		this.name = name;
	}
	
	public String getFormattedName() {
		return name;
	}
	
}
