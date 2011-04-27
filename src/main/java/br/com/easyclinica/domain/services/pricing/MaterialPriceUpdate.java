package br.com.easyclinica.domain.services.pricing;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;

@Component
public class MaterialPriceUpdate {

	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> materials) {
		
		for(ImportedStuff importedMaterial : materials) {
			PrecifiedMaterial precifiedMaterial = find(importedMaterial, plan.getPrecifiedMaterials());
			precifiedMaterial.setAmount(importedMaterial.getValue());
		}
		
		
	}

	private PrecifiedMaterial find(ImportedStuff importedMaterial,
			List<PrecifiedMaterial> precifiedMaterials) {
		for(PrecifiedMaterial m : precifiedMaterials) {
			if(m.getMaterial().getId() == importedMaterial.getId()) return m;
		}
		
		return null;
	}

}
