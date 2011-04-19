package br.com.easyclinica.domain.services.pricing;

import java.math.BigDecimal;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllProcedures;

@Component
public class ProcedurePriceUpdate {

	private final AllProcedures allProcedures;

	public ProcedurePriceUpdate(AllProcedures procedures) {
		this.allProcedures = procedures;
	}
	
	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> procedures) {
		
		for(ImportedStuff importedProcedure : procedures) {
			PrecifiedProcedure precifiedProcedure = find(importedProcedure, plan.getPrecifiedProcedures());
			if(doesntExist(precifiedProcedure)) {
				precifiedProcedure = plan.addPrecifiedProcedure(allProcedures.getById(importedProcedure.getId()), BigDecimal.ZERO);
			}
			
			precifiedProcedure.setFixedAmount(importedProcedure.getValue());
		}
		
		
	}

	private boolean doesntExist(PrecifiedProcedure precifiedProcedure) {
		return precifiedProcedure == null;
	}

	private PrecifiedProcedure find(ImportedStuff importedProcedure,
			List<PrecifiedProcedure> precifiedSpecialties) {
		for(PrecifiedProcedure m : precifiedSpecialties) {
			if(m.getProcedure().getId() == importedProcedure.getId()) return m;
		}
		
		return null;
	}

}
