package br.com.easyclinica.domain.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.easyclinica.domain.types.Address;
import br.com.easyclinica.domain.types.Email;
import br.com.easyclinica.domain.types.Money;
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
	@Embedded @AttributeOverride(name="money", column = @Column(name="ch"))
	private Money ch;
	@Embedded @AttributeOverride(name="name", column = @Column(name="contact"))
	private Name contact;
	@OneToOne(fetch=FetchType.EAGER) @JoinColumn(name="table_fk")	
	private ServicesTable table;
	
	protected HealthCarePlan() {}

	public HealthCarePlan(int id, Name name, Address address, Telephone telephone,
			Email email, Website website, Name contact,
			Observations observations, ServicesTable table, Money ch) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
		this.website = website;
		this.contact = contact;
		this.observations = observations;
		this.table = table;
		this.ch = ch;
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

	public Telephone getTelephone() {
		return telephone;
	}

	public Email getEmail() {
		return email;
	}

	public Website getWebsite() {
		return website;
	}

	public Name getContact() {
		return contact;
	}

	public Observations getObservations() {
		return observations;
	}
	
	public Money getCh() {
		return ch;
	}

	public ServicesTable getTable() {
		return table;
	}
	
	public static HealthCarePlan empty() {
		return new HealthCarePlan();
	}
}
