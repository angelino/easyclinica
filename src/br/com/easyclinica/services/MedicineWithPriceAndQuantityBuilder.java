package br.com.easyclinica.services;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.dto.MedicineWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.repositories.AllMedicines;

@Component
public class MedicineWithPriceAndQuantityBuilder {
	private final AllMedicines allMedicines;
	
	private Procedure procedure;
	private HealthCarePlan healthCarePlan;
	
	public MedicineWithPriceAndQuantityBuilder(AllMedicines allMedicines) {
		this.allMedicines = allMedicines;
	}

	public MedicineWithPriceAndQuantityBuilder ofTheProcedure(Procedure procedure) {
		this.procedure = procedure;			
		return this;
	}
	
	public MedicineWithPriceAndQuantityBuilder inTheHealthCarePlan(HealthCarePlan healthCarePlan) {
		this.healthCarePlan = healthCarePlan;			
		return this;
	}
	
	public List<MedicineWithPriceAndQuantity> execute(){
		return allMedicines.getMedicinesWithPriceAndQuantity(procedure, healthCarePlan);
	}
}
