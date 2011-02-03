package br.com.easyclinica.domain.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.easyclinica.domain.types.Address;

@Entity
public class HealthCarePlan {

	@Id @GeneratedValue
	private int id;
	
	private String name;
	@Embedded private Address address;
	private String telephone;
	private String email;
	private String website;
	private String observations;
	private double ch;
	private String contact;
	private boolean active;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="healthCarePlan") 
	private List<PrecifiedMaterial> precifiedMaterials;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="healthCarePlan")
	private List<PrecifiedMedicine> precifiedMedicines;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="healthCarePlan")
	private List<PrecifiedProcedure> precifiedProcedures;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="healthCarePlan")
	private List<PrecifiedSpecialty> precifiedSpecialties;
	
	public HealthCarePlan() {
		precifiedMaterials = new ArrayList<PrecifiedMaterial>();
		precifiedMedicines = new ArrayList<PrecifiedMedicine>();
		precifiedProcedures = new ArrayList<PrecifiedProcedure>();
		precifiedSpecialties = new ArrayList<PrecifiedSpecialty>();
	}
	public HealthCarePlan(int id) {
		this();
		this.id = id;
	}

	public static HealthCarePlan empty() {
		HealthCarePlan emptyPlan = new HealthCarePlan();
		emptyPlan.activate();
		return emptyPlan;
	}

	public void deactivate() {
		active = false;
	}

	public void activate() {
		active = true;
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

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	protected List<PrecifiedMaterial> getPrecifiedMaterials() {
		return Collections.unmodifiableList(precifiedMaterials);
	}

	protected List<PrecifiedMedicine> getPrecifiedMedicines() {
		return Collections.unmodifiableList(precifiedMedicines);
	}

	protected List<PrecifiedProcedure> getPrecifiedProcedures() {
		return Collections.unmodifiableList(precifiedProcedures);
	}

	public List<PrecifiedSpecialty> getPrecifiedSpecialties() {
		return precifiedSpecialties;
	}
	
	protected void setPrecifiedSpecialties(
			List<PrecifiedSpecialty> precifiedSpecialties) {
		this.precifiedSpecialties = precifiedSpecialties;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
