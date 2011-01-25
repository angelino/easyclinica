package br.com.easyclinica.domain.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Doctor {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Clinic clinic;
	
	private String name;
	private String crm;
	@ManyToOne(fetch=FetchType.EAGER)
	private Specialty specialty;
	private String telephone;
	private String email;
	private String observations;
	private boolean active;
	
	public Doctor() { }
	
	public Doctor(int id) {
		this.id = id;
	}
	
	public static Doctor empty() {
		return new Doctor();
	}

	public void deactivate() {
		this.active = false;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getId() {
		return id;
	}
	
	
}
