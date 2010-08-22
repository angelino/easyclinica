package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.types.Email;
import br.com.easyclinica.domain.types.HealthCareId;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Observations;
import br.com.easyclinica.domain.types.Telephone;

public class PatientBuilder {

	private Patient instance;
	
	public PatientBuilder() {
		instance = new Patient(
				0,
				new Name("John Doe"),
				new AddressBuilder().instance(),
				new Telephone("1234"),
				new Telephone("4567"),
				new Email("some@email.com"),
				new HealthCarePlanBuilder().instance(),
				new HealthCareId("123"),
				new Observations("some obs")
				); 
	}
	
	public Patient instance() {
		return instance;
	}

	public PatientBuilder withHealthCarePlan(HealthCarePlan plan) {
		instance = new Patient(
				instance.getId(),
				instance.getName(),
				instance.getAddress(),
				instance.getTelephone(),
				instance.getCellphone(),
				instance.getEmail(),
				plan,
				instance.getHealthCareId(),
				instance.getObservations()
				); 
		
		return this;
	}

	public PatientBuilder withName(String name) {
		instance = new Patient(
				instance.getId(),
				new Name(name),
				instance.getAddress(),
				instance.getTelephone(),
				instance.getCellphone(),
				instance.getEmail(),
				instance.getHealthCarePlan(),
				instance.getHealthCareId(),
				instance.getObservations()
				); 
		
		return this;
	}
}
