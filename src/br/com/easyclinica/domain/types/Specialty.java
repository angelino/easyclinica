package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Specialty {
	private String specialty;

	protected Specialty() {}
	public Specialty(String specialty) {
		this.specialty = specialty;
	}

	public String getSpecialty() {
		return specialty;
	}
	
	public String toString() {
		return getSpecialty();
	}
	
	public static Specialty empty() {
		return new Specialty("");
	}
}
