package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.types.Address;

public class PatientBuilder {

	private Patient instance;
	
	public PatientBuilder() {
		this(0);
	}
	
	public PatientBuilder(int id) {
		instance = new Patient(id);
		instance.setName("John Doe");
		instance.setAddress(new AddressBuilder().instance());
		instance.setTelephone("1234");
		instance.setCellphone("1234");
		instance.setEmail("some@email.com");
		instance.setHealthCarePlan(new HealthCarePlanBuilder().instance());
		instance.setObservations("some obs");
	}
	
	public Patient instance() {
		return instance;
	}

	public PatientBuilder withHealthCarePlan(HealthCarePlan plan) {
		instance.setHealthCarePlan(plan); 
		
		return this;
	}

	public PatientBuilder withName(String name) {
		instance.setName(name);
		return this;
	}

	public PatientBuilder withTelephone(String phone) {
		instance.setTelephone(phone);
		return this;
	}

	public PatientBuilder withAddress(Address address) {
		instance.setAddress(address);
		return this;
	}

	public PatientBuilder withCellphone(String cellphone) {
		instance.setCellphone(cellphone);
		return this;
	}

	public PatientBuilder withEmail(String email) {
		instance.setEmail(email);
		return this;
	}

	public PatientBuilder withObservations(String observations) {
		instance.setObservations(observations);
		return this;
	}

	public PatientBuilder withCpf(String cpf) {
		instance.setCpf(cpf);
		return this;
	}
}
