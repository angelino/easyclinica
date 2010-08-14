package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.ServicesTable;
import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Email;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Observations;
import br.com.easyclinica.domain.types.Telephone;
import br.com.easyclinica.domain.types.Website;

public class HealthCarePlanBuilder {

	private HealthCarePlan plan;
	
	public HealthCarePlanBuilder() {
		plan = new HealthCarePlan(
				new Name("Amil"),
				new AddressBuilder().instance(),
				new Telephone("123"),
				new Email("email@email.com"),
				new Website("website.com"),
				new Name("contact"),
				new Observations("obs"),
				new ServicesTable(new Name("table")),
				new CH(20)
		);
	}
	
	public HealthCarePlanBuilder withName(String name) {
		plan = new HealthCarePlan(
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
				plan.getName(),
				plan.getAddress(),
				plan.getTelephone(),
				plan.getEmail(),
				plan.getWebsite(),
				plan.getContact(),
				plan.getObservations(),
				plan.getTable(),
				new CH(ch)
		);
		return this;
	}
	
	public HealthCarePlan instance() {
		return plan;
	}
	
}
