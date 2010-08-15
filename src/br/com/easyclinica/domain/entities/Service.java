package br.com.easyclinica.domain.entities;

import java.util.Collections;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Name;

@Entity
public class Service {

	@Id
	@GeneratedValue
	private int id;
	
	@Embedded private Name name;
	@Embedded private CH ch;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="table_fk")
	private ServicesTable table;
	
	@ManyToMany
	@JoinTable(name="service_materials", joinColumns=@JoinColumn(name="service_fk"), inverseJoinColumns=@JoinColumn(name="material_fk"))
	private List<Material> materials;

	protected Service() {}
	
	public Service(int id, Name name, CH ch, ServicesTable table) {
		this.id = id;
		this.name = name;
		this.ch = ch;
		this.table = table;
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

	public ServicesTable getTable() {
		return table;
	}
	
	public List<Material> getMaterials() {
		return Collections.unmodifiableList(materials);
	}
}
