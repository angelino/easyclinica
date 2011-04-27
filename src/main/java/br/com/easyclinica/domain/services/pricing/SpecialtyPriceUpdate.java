package br.com.easyclinica.domain.services.pricing;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;

@Component
public class SpecialtyPriceUpdate {

	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> specialties) {
		
		for(ImportedStuff importedSpecialty : specialties) {
			PrecifiedSpecialty precifiedSpecialty = find(importedSpecialty, plan.getPrecifiedSpecialties());
			precifiedSpecialty.setAmount(importedSpecialty.getValue());
		}
		
		
	}

	private PrecifiedSpecialty find(ImportedStuff importedSpecialty,
			List<PrecifiedSpecialty> precifiedSpecialties) {
		for(PrecifiedSpecialty m : precifiedSpecialties) {
			if(m.getSpecialty().getId() == importedSpecialty.getId()) return m;
		}
		
		return null;
	}

}
