package br.com.easyclinica.domain.services.pricing;

import java.math.BigDecimal;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllMedicines;

@Component
public class MedicinePriceUpdate {

	private final AllMedicines allMedicines;

	public MedicinePriceUpdate(AllMedicines medicines) {
		this.allMedicines = medicines;
	}
	
	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> medicines) {
		
		for(ImportedStuff importedMedicine : medicines) {
			PrecifiedMedicine precifiedMedicine = find(importedMedicine, plan.getPrecifiedMedicines());
			if(doesntExist(precifiedMedicine)) {
				precifiedMedicine = plan.addPrecifiedMedicine(allMedicines.getById(importedMedicine.getId()), BigDecimal.ZERO);
				
			}
			
			precifiedMedicine.setAmount(importedMedicine.getValue());
		}
		
		
	}

	private boolean doesntExist(PrecifiedMedicine precifiedMedicine) {
		return precifiedMedicine == null;
	}

	private PrecifiedMedicine find(ImportedStuff importedMedicine,
			List<PrecifiedMedicine> precifiedMedicines) {
		for(PrecifiedMedicine m : precifiedMedicines) {
			if(m.getMedicine().getId() == importedMedicine.getId()) return m;
		}
		
		return null;
	}

}
