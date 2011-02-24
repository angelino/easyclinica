package br.com.easyclinica.domain.services.pricing;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllMedicines;

@Component
public class MedicinePriceUpdate {

	private final AllMedicines medicines;

	public MedicinePriceUpdate(AllMedicines medicines) {
		this.medicines = medicines;
	}
	
	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> medicines) {
		
		for(ImportedStuff importedMedicine : medicines) {
			PrecifiedMedicine precifiedMedicine = find(importedMedicine, plan.getPrecifiedMedicines());
			if(doesntExist(precifiedMedicine)) {
				precifiedMedicine = newOneFrom(importedMedicine);
				plan.addPrecifiedMedicine(precifiedMedicine);
			}
			
			precifiedMedicine.setAmount(importedMedicine.getValue());
		}
		
		
	}

	private boolean doesntExist(PrecifiedMedicine precifiedMedicine) {
		return precifiedMedicine == null;
	}

	private PrecifiedMedicine newOneFrom(ImportedStuff importedMaterial) {
		PrecifiedMedicine pm = new PrecifiedMedicine();
		pm.setMedicine(medicines.getById(importedMaterial.getId()));
		
		return pm;
	}

	private PrecifiedMedicine find(ImportedStuff importedMedicine,
			List<PrecifiedMedicine> precifiedMedicines) {
		for(PrecifiedMedicine m : precifiedMedicines) {
			if(m.getMedicine().getId() == importedMedicine.getId()) return m;
		}
		
		return null;
	}

}
