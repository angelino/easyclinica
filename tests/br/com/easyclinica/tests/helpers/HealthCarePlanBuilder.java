package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.types.Email;
import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Observations;
import br.com.easyclinica.domain.types.Telephone;
import br.com.easyclinica.domain.types.Website;

public class HealthCarePlanBuilder {

	private HealthCarePlan plan;
	
	public HealthCarePlanBuilder() {
		plan = new HealthCarePlan(
				0,
				new ClinicBuilder().instance(),
				new Name("Amil"),
				new AddressBuilder().instance(),
				new Telephone("123"),
				new Email("email@email.com"),
				new Website("website.com"),
				new Name("contact"),
				new Observations("obs"),
				new Money(20)
		);
	}
	
	public HealthCarePlanBuilder withName(String name) {
		plan = new HealthCarePlan(
				plan.getId(),
				plan.getClinic(),
				new Name(name),
				plan.getAddress(),
				plan.getTelephone(),
				plan.getEmail(),
				plan.getWebsite(),
				plan.getContact(),
				plan.getObservations(),
				plan.getCh()
		);
		
		return this;
	}

	public HealthCarePlanBuilder withCh(double ch) {
		plan = new HealthCarePlan(
				plan.getId(),
				plan.getClinic(),
				plan.getName(),
				plan.getAddress(),
				plan.getTelephone(),
				plan.getEmail(),
				plan.getWebsite(),
				plan.getContact(),
				plan.getObservations(),
				new Money(ch)
		);
		return this;
	}
	
	public HealthCarePlan instance() {
		return plan;
	}

	public HealthCarePlanBuilder withId(int id) {
		plan = new HealthCarePlan(
				id,
				plan.getClinic(),
				plan.getName(),
				plan.getAddress(),
				plan.getTelephone(),
				plan.getEmail(),
				plan.getWebsite(),
				plan.getContact(),
				plan.getObservations(),
				plan.getCh()
		);
		return this;
	}
	
	public HealthCarePlanBuilder ofTheClinic(Clinic clinic) {
		plan = new HealthCarePlan(
				plan.getId(),
				clinic,
				plan.getName(),
				plan.getAddress(),
				plan.getTelephone(),
				plan.getEmail(),
				plan.getWebsite(),
				plan.getContact(),
				plan.getObservations(),
				plan.getCh()
		);
		return this;
	}
	
}
