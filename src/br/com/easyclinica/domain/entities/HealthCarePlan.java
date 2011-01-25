package br.com.easyclinica.domain.entities;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.easyclinica.domain.types.Address;

@Entity
public class HealthCarePlan {

	@Id @GeneratedValue
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="clinic_fk")
	private Clinic clinic;
	
	private String name;
	@Embedded private Address address;
	private String telephone;
	private String email;
	private String website;
	private String observations;
	private double ch;
	private String contact;
	private boolean active;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="healthCarePlan") 
	private List<PrecifiedMaterial> precifiedMaterials;
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="healthCarePlan")
	private List<PrecifiedMedicine> precifiedMedicines;
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="healthCarePlan")
	private List<PrecifiedProcedure> precifiedProcedures;
	

	public static HealthCarePlan empty() {
		return new HealthCarePlan();
	}

	public void deactivate() {
		active = false;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public double getCh() {
		return ch;
	}

	public void setCh(double ch) {
		this.ch = ch;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	public List<PrecifiedMaterial> getPrecifiedMaterials() {
		return Collections.unmodifiableList(precifiedMaterials);
	}

	public List<PrecifiedMedicine> getPrecifiedMedicines() {
		return Collections.unmodifiableList(precifiedMedicines);
	}

	public List<PrecifiedProcedure> getPrecifiedProcedures() {
		return Collections.unmodifiableList(precifiedProcedures);
	}

}
