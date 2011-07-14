package br.com.easyclinica.domain.services.pricing.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllMedicines;

@Component
public class MedicinePriceUpdate {

	private final AllMedicines allMedicines;

	public MedicinePriceUpdate(AllMedicines allMedicines) {
		this.allMedicines = allMedicines;
	}
	
	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> medicines) {
		
		Map<Integer, PrecifiedMedicine> map = generateMap(plan);
		
		for(ImportedStuff importedMedicine : medicines) {
			PrecifiedMedicine precifiedMedicine = map.get(importedMedicine.getId());
			if(precifiedMedicine != null) {
			precifiedMedicine.setAmount(importedMedicine.getValue());
			}
			else {
				plan.addPrecifiedMedicine(allMedicines.getById(importedMedicine.getId()), importedMedicine.getValue());
			}
		}
	}

	private Map<Integer, PrecifiedMedicine> generateMap(HealthCarePlan plan) {
		Map<Integer, PrecifiedMedicine> map = new HashMap<Integer, PrecifiedMedicine>();
		for(PrecifiedMedicine pm : plan.getPrecifiedMedicines()) {
			map.put(pm.getMedicine().getId(), pm);
		}
		return map;
	}

}
