package br.com.easyclinica.domain.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.easyclinica.domain.types.Active;
import br.com.easyclinica.domain.types.Domain;
import br.com.easyclinica.domain.types.Name;

@Entity
public class Clinic {

	@Id @GeneratedValue
	private int id;
	
	@Embedded private Name name;
	@Embedded private Domain domain;
	@Embedded private Active active;
	
	protected Clinic() {}
	public Clinic(int id, Name name, Domain domain) {
		this.setId(id);
		this.name = name;
		this.domain = domain;
		this.active = Active.active();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setName(Name name) {
		this.name = name;
	}
	public Name getName() {
		return name;
	}
	
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	public Domain getDomain() {
		return domain;
	}
	
	public void setActive(Active active) {
		this.active = active;
	}
	public Active getActive() {
		return active;
	}
	
}
