package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Name {
	private String name;
	
	protected Name() {}
	public Name(String name) {
		this.name = name;
	}
	
	public String get() {
		return name;
	}
	
	public String toString() {
		return get();
	}
	public static Name empty() {
		return new Name("");
	}
}
