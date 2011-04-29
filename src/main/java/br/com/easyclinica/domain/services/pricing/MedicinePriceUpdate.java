package br.com.easyclinica.domain.services.pricing;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;

@Component
public class MedicinePriceUpdate {

	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> medicines) {
		
		for(ImportedStuff importedMedicine : medicines) {
			PrecifiedMedicine precifiedMedicine = find(importedMedicine, plan.getPrecifiedMedicines());
			
			precifiedMedicine.setAmount(importedMedicine.getValue());
		}
	}

	private PrecifiedMedicine find(ImportedStuff importedMedicine,
			List<PrecifiedMedicine> precifiedMedicines) {
		for(PrecifiedMedicine m : precifiedMedicines) {
			if(m.getMedicine().getId() == importedMedicine.getId()) return m;
		}
		
		return null;
	}

}
