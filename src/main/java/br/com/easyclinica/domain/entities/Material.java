package br.com.easyclinica.domain.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Material {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Unit unit;

	public Material() {
	}

	public Material(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Unit getUnit() {
		return unit;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
