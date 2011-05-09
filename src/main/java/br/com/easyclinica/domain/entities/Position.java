package br.com.easyclinica.domain.entities;

public enum Position {

	FINANCIAL ("position.financial"), 
	DOCTOR ("position.doctor"), 
	ATTENDANT("position.attendant"),
	OWNER("position.owner");
	
	private String key;
	
	Position(String key) {
		this.key = key;
	}
	
	public String getLocaleKey() {
		return key;
	}
	
}
