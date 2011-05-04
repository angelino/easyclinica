package br.com.easyclinica.domain.services.pricing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;

@Component
public class MaterialPriceUpdate {

	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> materials) {
		
		Map<Integer, PrecifiedMaterial> map = generateMap(plan);
		
		for(ImportedStuff importedMaterial : materials) {
			PrecifiedMaterial precifiedMaterial = map.get(importedMaterial.getId());
			precifiedMaterial.setAmount(importedMaterial.getValue());
		}
	}

	private Map<Integer, PrecifiedMaterial> generateMap(HealthCarePlan plan) {
		Map<Integer, PrecifiedMaterial> map = new HashMap<Integer, PrecifiedMaterial>();
		for(PrecifiedMaterial pm : plan.getPrecifiedMaterials()) {
			map.put(pm.getMaterial().getId(), pm);
		}
		return map;
	}

}
