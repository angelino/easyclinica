package br.com.easyclinica.domain.services.pricing;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllSpecialties;

@Component
public class SpecialtyPriceUpdate {

	private final AllSpecialties specialties;

	public SpecialtyPriceUpdate(AllSpecialties specialties) {
		this.specialties = specialties;
	}
	
	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> specialties) {
		
		for(ImportedStuff importedSpecialty : specialties) {
			PrecifiedSpecialty precifiedSpecialty = find(importedSpecialty, plan.getPrecifiedSpecialties());
			if(doesntExist(precifiedSpecialty)) {
				precifiedSpecialty = newOneFrom(importedSpecialty);
				plan.addPrecifiedSpecialty(precifiedSpecialty);
			}
			
			precifiedSpecialty.setAmount(importedSpecialty.getValue());
		}
		
		
	}

	private boolean doesntExist(PrecifiedSpecialty precifiedSpecialty) {
		return precifiedSpecialty == null;
	}

	private PrecifiedSpecialty newOneFrom(ImportedStuff importedSpecialty) {
		PrecifiedSpecialty pm = new PrecifiedSpecialty();
		pm.setSpecialty(specialties.getById(importedSpecialty.getId()));
		
		return pm;
	}

	private PrecifiedSpecialty find(ImportedStuff importedSpecialty,
			List<PrecifiedSpecialty> precifiedSpecialties) {
		for(PrecifiedSpecialty m : precifiedSpecialties) {
			if(m.getSpecialty().getId() == importedSpecialty.getId()) return m;
		}
		
		return null;
	}

}
