package br.com.easyclinica.domain.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Name;

@Entity
public class Material {
	@Id
	@GeneratedValue
	private int id;
	
	@Embedded private Name name;
	@Embedded private CH ch;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="table_fk")
	private ServicesTable table;
	
	protected Material() {}

	public Material(int id) {
		this.id = id;
	}
	
	public Material(ServicesTable table, int id, Name name, CH ch) {
		this.table = table;
		this.id = id;
		this.name = name;
		this.ch = ch;
	}
	
	public Material(ServicesTable table, Name name, CH ch) {
		this.table = table;
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

	public ServicesTable getTable() {
		return table;
	}

}
