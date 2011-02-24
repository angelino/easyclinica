package br.com.easyclinica.tests.helpers;

import java.math.BigDecimal;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;

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
	
	public HealthCarePlanBuilder withPrecifiedMaterial(PrecifiedMaterial pm) {
		plan.addPrecifiedMaterial(pm);
		return this;
	}
	
	public HealthCarePlanBuilder active() {
		plan.activate();
		return this;
	}

	public HealthCarePlanBuilder withPrecifiedMedicine(PrecifiedMedicine pm) {
		plan.addPrecifiedMedicine(pm);
		return this;
	}

	public HealthCarePlanBuilder withPrecifiedSpecialty(PrecifiedSpecialty pm) {
		plan.addPrecifiedSpecialty(pm);
		return this;
	}

	public HealthCarePlanBuilder withPrecifiedProcedure(PrecifiedProcedure pm) {
		plan.addPrecifiedProcedure(pm);
		return this;
	}

}
