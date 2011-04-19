package br.com.easyclinica.domain.services.pricing;

import java.math.BigDecimal;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllSpecialties;

@Component
public class SpecialtyPriceUpdate {

	private final AllSpecialties allSpecialties;

	public SpecialtyPriceUpdate(AllSpecialties specialties) {
		this.allSpecialties = specialties;
	}
	
	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> specialties) {
		
		for(ImportedStuff importedSpecialty : specialties) {
			PrecifiedSpecialty precifiedSpecialty = find(importedSpecialty, plan.getPrecifiedSpecialties());
			if(doesntExist(precifiedSpecialty)) {
				precifiedSpecialty = plan.addPrecifiedSpecialty(allSpecialties.getById(importedSpecialty.getId()), BigDecimal.ZERO);
			}
			
			precifiedSpecialty.setAmount(importedSpecialty.getValue());
		}
		
		
	}

	private boolean doesntExist(PrecifiedSpecialty precifiedSpecialty) {
		return precifiedSpecialty == null;
	}

	private PrecifiedSpecialty find(ImportedStuff importedSpecialty,
			List<PrecifiedSpecialty> precifiedSpecialties) {
		for(PrecifiedSpecialty m : precifiedSpecialties) {
			if(m.getSpecialty().getId() == importedSpecialty.getId()) return m;
		}
		
		return null;
	}

}
