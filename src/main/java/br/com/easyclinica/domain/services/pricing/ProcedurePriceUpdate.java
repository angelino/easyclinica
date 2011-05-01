package br.com.easyclinica.domain.services.pricing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;

@Component
public class ProcedurePriceUpdate {

	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> procedures) {
		
		Map<Integer, PrecifiedProcedure> map = generateMap(plan);
		
		for(ImportedStuff importedProcedure : procedures) {
			PrecifiedProcedure precifiedProcedure = map.get(importedProcedure.getId());
			
			precifiedProcedure.setFixedAmount(importedProcedure.getValue());
			precifiedProcedure.setCh(importedProcedure.getCh());
			precifiedProcedure.setRoomTaxAmount(importedProcedure.getRoomTax());
		}
		
		
	}

	private Map<Integer, PrecifiedProcedure> generateMap(HealthCarePlan plan) {
		Map<Integer, PrecifiedProcedure> map = new HashMap<Integer, PrecifiedProcedure>();
		for(PrecifiedProcedure pm : plan.getPrecifiedProcedures()) {
			map.put(pm.getProcedure().getId(), pm);
		}
		return map;
	}

	
}
