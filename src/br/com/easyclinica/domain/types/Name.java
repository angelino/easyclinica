package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Name {
	private String name;
	
	protected Name() {}
	public Name(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return getName();
	}
	public static Name empty() {
		return new Name("");
	}
}
