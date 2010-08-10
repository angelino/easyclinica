package br.com.easyclinica.domain.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.easyclinica.domain.types.CRM;
import br.com.easyclinica.domain.types.Email;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Observations;
import br.com.easyclinica.domain.types.Specialty;
import br.com.easyclinica.domain.types.Telephone;

@Entity
public class Doctor {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Embedded private Name name;
	@Embedded private CRM crm;
	@Embedded private Specialty specialty;
	@Embedded private Telephone telephone;
	@Embedded private Email email;
	@Embedded private Observations observations;
	
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
	
	public void setCrm(CRM crm) {
		this.crm = crm;
	}
	
	public CRM getCrm() {
		return crm;
	}
	
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setTelephone(Telephone telephone) {
		this.telephone = telephone;
	}
	
	public Telephone getTelephone() {
		return telephone;
	}

	public void setEmail(Email email) {
		this.email = email;
	}
	
	public Email getEmail() {
		return email;
	}
	
	public void setObservations(Observations observations) {
		this.observations = observations;
	}
	
	public Observations getObservations() {
		return observations;
	}
}
