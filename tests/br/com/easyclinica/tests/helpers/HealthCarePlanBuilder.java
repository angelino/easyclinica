package br.com.easyclinica.tests.helpers;

import java.math.BigDecimal;

import br.com.easyclinica.domain.entities.HealthCarePlan;

public class HealthCarePlanBuilder {

	private HealthCarePlan plan;
	
	public HealthCarePlanBuilder() {
		this(0);
	}
	
	public HealthCarePlanBuilder(int id) {
		plan = new HealthCarePlan(id);
		plan.setName("Amil");
		plan.setAddress(new AddressBuilder().instance());
		plan.setTelephone("123");
		plan.setEmail("email@email.com");
		plan.setWebsite("website.com");
		plan.setContact("contact");
		plan.setObservations("obs");
		plan.setCh(new BigDecimal(20.0));
	}
	
	public HealthCarePlanBuilder withName(String name) {
		plan.setName(name);
		return this;
	}

	public HealthCarePlanBuilder withCh(BigDecimal ch) {
		plan.setCh(ch);
		return this;
	}
	
	public HealthCarePlan instance() {
		return plan;
	}

	public HealthCarePlanBuilder notActive() {
		plan.deactivate();
		return this;
	}

	public HealthCarePlanBuilder withPeriodToReturn(int periodToReturn) {
		plan.setPeriodToReturn(periodToReturn);
		return this;
	}
	
	public HealthCarePlanBuilder active() {
		plan.activate();
		return this;
	}

}
