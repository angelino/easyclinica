package br.com.easyclinica.domain.entities;

import br.com.easyclinica.domain.types.Address;
import br.com.easyclinica.domain.types.HealthCareId;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Observations;

public class Patient {

	private final Name name;
	private final Address address;
	private final HealthCarePlan healthCarePlan;
	private final HealthCareId healthCareId;
	private final Observations observations;

	public Patient(Name name, Address address, HealthCarePlan healthCarePlan,
			HealthCareId healthCareId, Observations observations) {
		this.name = name;
		this.address = address;
		this.healthCarePlan = healthCarePlan;
		this.healthCareId = healthCareId;
		this.observations = observations;
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

}
