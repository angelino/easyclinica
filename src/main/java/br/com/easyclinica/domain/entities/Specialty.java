package br.com.easyclinica.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Specialty {

	@Id @GeneratedValue
	private int id;
	private String name;
	
	public Specialty(){}
	public Specialty(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
