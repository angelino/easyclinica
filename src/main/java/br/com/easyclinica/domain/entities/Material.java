package br.com.easyclinica.domain.entities;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Material {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Unit unit;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="material")
	private List<PrecifiedMaterial> precifiedMaterials;

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

	public List<PrecifiedMaterial> getPrecifiedMaterials() {
		return Collections.unmodifiableList(precifiedMaterials);
	}

	
}
