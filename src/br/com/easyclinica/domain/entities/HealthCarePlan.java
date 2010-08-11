package br.com.easyclinica.domain.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.easyclinica.domain.types.Address;
import br.com.easyclinica.domain.types.Email;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Observations;
import br.com.easyclinica.domain.types.Telephone;
import br.com.easyclinica.domain.types.Website;

@Entity
public class HealthCarePlan {

	@Id
	@GeneratedValue
	private int id;
	
	@Embedded private Name name;
	@Embedded private Address address;
	@Embedded private Telephone telephone;
	@Embedded private Email email;
	@Embedded private Website website;
	@Embedded private Observations observations;
	@Embedded @AttributeOverride(name="name", column = @Column(name="contact"))
	private Name contact;
	
	protected HealthCarePlan() {}
	
	public HealthCarePlan(Name name) {
		this.name = name;
		this.address = Address.empty();
		this.telephone = Telephone.empty();
		this.email = Email.empty();
		this.website = Website.empty();
		this.contact = Name.empty();
		this.observations = Observations.empty();
	}
	
	public HealthCarePlan(Name name, Address address, Telephone telephone,
			Email email, Website website, Name contact,
			Observations observations) {
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
		this.website = website;
		this.contact = contact;
		this.observations = observations;
	}
	
	public HealthCarePlan(int id, Name name, Address address, Telephone telephone,
			Email email, Website website, Name contact,
			Observations observations) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
		this.website = website;
		this.contact = contact;
		this.observations = observations;
	}

	public Name getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Telephone getTelephone() {
		return telephone;
	}

	public void setTelephone(Telephone telephone) {
		this.telephone = telephone;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	public Name getContact() {
		return contact;
	}

	public void setContact(Name contact) {
		this.contact = contact;
	}

	public Observations getObservations() {
		return observations;
	}

	public void setObservations(Observations observations) {
		this.observations = observations;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
	
}
