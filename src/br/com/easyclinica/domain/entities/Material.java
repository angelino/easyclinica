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
	
	public Material(Name name, CH ch, ServicesTable table) {
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

}
