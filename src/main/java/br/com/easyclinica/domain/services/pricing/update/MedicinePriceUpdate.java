package br.com.easyclinica.domain.services.pricing.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;

@Component
public class MedicinePriceUpdate {

	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> medicines) {
		
		Map<Integer, PrecifiedMedicine> map = generateMap(plan);
		
		for(ImportedStuff importedMedicine : medicines) {
			PrecifiedMedicine precifiedMedicine = map.get(importedMedicine.getId());
			
			precifiedMedicine.setAmount(importedMedicine.getValue());
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
