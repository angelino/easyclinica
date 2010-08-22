package br.com.easyclinica.domain.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.easyclinica.domain.types.Address;
import br.com.easyclinica.domain.types.Email;
import br.com.easyclinica.domain.types.HealthCareId;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Observations;
import br.com.easyclinica.domain.types.Telephone;

@Entity
public class Patient {

	@Id @GeneratedValue
	private int id;
	@Embedded private Name name;
	@Embedded private Address address;
	@ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="healthcareplan_fk")
	private HealthCarePlan healthCarePlan;
	@Embedded private HealthCareId healthCareId;
	@Embedded private Observations observations;
	@Embedded @AttributeOverride(name="telephone", column = @Column(name="cellphone"))
	private Telephone cellphone;
	@Embedded private Telephone telephone;
	@Embedded private Email email;

	protected Patient() {}
	public Patient(int id, Name name, Address address, Telephone telephone, Telephone cellphone, Email email, HealthCarePlan healthCarePlan,
			HealthCareId healthCareId, Observations observations) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.cellphone = cellphone;
		this.email = email;
		this.healthCarePlan = healthCarePlan;
		this.healthCareId = healthCareId;
		this.observations = observations;
	}
	
	public int getId() {
		return id;
	}

	public Name getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public HealthCarePlan getHealthCarePlan() {
		return healthCarePlan;
	}

	public HealthCareId getHealthCareId() {
		return healthCareId;
	}

	public Observations getObservations() {
		return observations;
	}

	public Telephone getCellphone() {
		return cellphone;
	}

	public Telephone getTelephone() {
		return telephone;
	}
	
	public Email getEmail() {
		return email;
	}
	public static Patient empty() {
		return new Patient(
				0,
				Name.empty(),
				Address.empty(),
				Telephone.empty(),
				Telephone.empty(),
				Email.empty(),
				new HealthCarePlan(),
				HealthCareId.empty(),
				Observations.empty()
		);
	}

}
