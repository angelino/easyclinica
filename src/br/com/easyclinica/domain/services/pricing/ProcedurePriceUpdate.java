package br.com.easyclinica.domain.services.pricing;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllProcedures;

@Component
public class ProcedurePriceUpdate {

	private final AllProcedures procedures;

	public ProcedurePriceUpdate(AllProcedures procedures) {
		this.procedures = procedures;
	}
	
	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> procedures) {
		
		for(ImportedStuff importedProcedure : procedures) {
			PrecifiedProcedure precifiedProcedure = find(importedProcedure, plan.getPrecifiedProcedures());
			if(doesntExist(precifiedProcedure)) {
				precifiedProcedure = newOneFrom(importedProcedure);
				plan.addPrecifiedProcedure(precifiedProcedure);
			}
			
			precifiedProcedure.setFixedAmount(importedProcedure.getValue());
		}
		
		
	}

	private boolean doesntExist(PrecifiedProcedure precifiedProcedure) {
		return precifiedProcedure == null;
	}

	private PrecifiedProcedure newOneFrom(ImportedStuff importedProcedure) {
		PrecifiedProcedure pm = new PrecifiedProcedure();
		pm.setProcedure(procedures.getById(importedProcedure.getId()));
		
		return pm;
	}

	private PrecifiedProcedure find(ImportedStuff importedProcedure,
			List<PrecifiedProcedure> precifiedSpecialties) {
		for(PrecifiedProcedure m : precifiedSpecialties) {
			if(m.getProcedure().getId() == importedProcedure.getId()) return m;
		}
		
		return null;
	}

}
