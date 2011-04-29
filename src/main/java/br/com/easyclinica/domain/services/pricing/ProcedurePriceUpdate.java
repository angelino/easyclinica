package br.com.easyclinica.domain.services.pricing;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;

@Component
public class ProcedurePriceUpdate {

	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> procedures) {
		
		for(ImportedStuff importedProcedure : procedures) {
			PrecifiedProcedure precifiedProcedure = find(importedProcedure, plan.getPrecifiedProcedures());
			precifiedProcedure.setFixedAmount(importedProcedure.getValue());
			precifiedProcedure.setCh(importedProcedure.getCh());
			precifiedProcedure.setRoomTaxAmount(importedProcedure.getRoomTax());
		}
		
		
	}

	private PrecifiedProcedure find(ImportedStuff importedProcedure,
			List<PrecifiedProcedure> precifiedSpecialties) {
		for(PrecifiedProcedure m : precifiedSpecialties) {
			if(m.getProcedure().getId() == importedProcedure.getId()) return m;
		}
		
		return null;
	}

}
