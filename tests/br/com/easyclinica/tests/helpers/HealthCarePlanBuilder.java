package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.ServicesTable;
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
				new Name("Amil"),
				new AddressBuilder().instance(),
				new Telephone("123"),
				new Email("email@email.com"),
				new Website("website.com"),
				new Name("contact"),
				new Observations("obs"),
				new ServicesTable(new Name("table")),
				new Money(20)
		);
	}
	
	public HealthCarePlanBuilder withName(String name) {
		plan = new HealthCarePlan(
				plan.getId(),
				new Name(name),
				plan.getAddress(),
				plan.getTelephone(),
				plan.getEmail(),
				plan.getWebsite(),
				plan.getContact(),
				plan.getObservations(),
				plan.getTable(),
				plan.getCh()
		);
		
		return this;
	}

	public HealthCarePlanBuilder withCh(double ch) {
		plan = new HealthCarePlan(
				plan.getId(),
				plan.getName(),
				plan.getAddress(),
				plan.getTelephone(),
				plan.getEmail(),
				plan.getWebsite(),
				plan.getContact(),
				plan.getObservations(),
				plan.getTable(),
				new Money(ch)
		);
		return this;
	}
	
	public HealthCarePlan instance() {
		return plan;
	}

	public HealthCarePlanBuilder withTable(ServicesTable servicesTable) {
		plan = new HealthCarePlan(
				plan.getId(),
				plan.getName(),
				plan.getAddress(),
				plan.getTelephone(),
				plan.getEmail(),
				plan.getWebsite(),
				plan.getContact(),
				plan.getObservations(),
				servicesTable,
				plan.getCh()
		);
		return this;
	}

	public HealthCarePlanBuilder withId(int id) {
		plan = new HealthCarePlan(
				id,
				plan.getName(),
				plan.getAddress(),
				plan.getTelephone(),
				plan.getEmail(),
				plan.getWebsite(),
				plan.getContact(),
				plan.getObservations(),
				plan.getTable(),
				plan.getCh()
		);
		return this;
	}
	
}
