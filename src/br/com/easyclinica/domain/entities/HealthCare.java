package br.com.easyclinica.domain.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.easyclinica.domain.types.Name;

@Entity
public class HealthCare {

	@Id
	@GeneratedValue
	private int id;
	
	@Embedded
	private Name name;
	
	protected HealthCare() {}
	
	public HealthCare(Name name) {
		this.name = name;
	}
	
	public Name getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
}
