package br.com.easyclinica.domain.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import br.com.easyclinica.domain.types.Name;

@Entity
public class ServicesTable {

	@Id
	@GeneratedValue
	private int id;
	
	@Embedded 
	private Name name;

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="table_fk")
	private List<Service> services;

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="table_fk")	
	private List<Material> materials;
	
	protected ServicesTable() {}
	public ServicesTable(Name name) {
		this.name = name;
		this.services = new ArrayList<Service>();
		this.materials = new ArrayList<Material>();
	}
	
	public ServicesTable(Name name, List<Service> services, List<Material> materials) {
		this.name = name;
		this.services = services;
		this.materials = materials;
	}

	public int getId() {
		return id;
	}

	public Name getName() {
		return name;
	}

	public List<Service> getServices() {
		return Collections.unmodifiableList(services);
	}

	public List<Material> getMaterials() {
		return Collections.unmodifiableList(materials);
	}
}
