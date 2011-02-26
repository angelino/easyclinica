package br.com.easyclinica.domain.services.pricing;

import java.math.BigDecimal;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllMaterials;

@Component
public class MaterialPriceUpdate {

	private final AllMaterials allMaterials;

	public MaterialPriceUpdate(AllMaterials materials) {
		this.allMaterials = materials;
	}
	
	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> materials) {
		
		for(ImportedStuff importedMaterial : materials) {
			PrecifiedMaterial precifiedMaterial = find(importedMaterial, plan.getPrecifiedMaterials());
			if(doesntExist(precifiedMaterial)) {
				precifiedMaterial = plan.addPrecifiedMaterial(allMaterials.getById(importedMaterial.getId()), BigDecimal.ZERO);
			}
			
			precifiedMaterial.setAmount(importedMaterial.getValue());
		}
		
		
	}

	private boolean doesntExist(PrecifiedMaterial precifiedMaterial) {
		return precifiedMaterial == null;
	}

	private PrecifiedMaterial find(ImportedStuff importedMaterial,
			List<PrecifiedMaterial> precifiedMaterials) {
		for(PrecifiedMaterial m : precifiedMaterials) {
			if(m.getMaterial().getId() == importedMaterial.getId()) return m;
		}
		
		return null;
	}

}
