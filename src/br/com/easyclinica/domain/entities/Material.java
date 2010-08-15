package br.com.easyclinica.domain.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Name;

@Entity
public class Material {
	@Id
	@GeneratedValue
	private int id;
	
	@Embedded private Name name;
	@Embedded private CH ch;

	protected Material() {}

	public Material(int id, Name name, CH ch) {
		this.id = id;
		this.name = name;
		this.ch = ch;
	}
	
	public Material(Name name, CH ch) {
		this.id = 0;
		this.name = name;
		this.ch = ch;
	}

	public int getId() {
		return id;
	}

	public Name getName() {
		return name;
	}

	public CH getCh() {
		return ch;
	}

}
