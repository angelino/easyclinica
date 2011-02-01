package br.com.easyclinica.domain.entities;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.easyclinica.domain.types.Address;

@Entity
public class Patient {

	@Id @GeneratedValue
	private int id;
	private String name;
	@Embedded private Address address;
	@ManyToOne(fetch=FetchType.EAGER)
	private HealthCarePlan healthCarePlan;
	private String observations;
	private String cellphone;
	private String telephone;
	private String email;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="patient")
	private List<Appointment> appointments;
	
	public Patient() {}
	public Patient(int id) {
		this.id = id;
	}
	
	public static Patient empty() {
		return new Patient();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public HealthCarePlan getHealthCarePlan() {
		return healthCarePlan;
	}

	public void setHealthCarePlan(HealthCarePlan healthCarePlan) {
		this.healthCarePlan = healthCarePlan;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	protected void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	

}
