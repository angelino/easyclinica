package br.com.easyclinica.domain.entities;

public enum AssistantType {
	ASSISTANT ("Assistente");
	
	private String name;
	
	AssistantType(String name) {
		this.name = name;
	}
	
	public String getFormattedName() {
		return name;
	}
}
