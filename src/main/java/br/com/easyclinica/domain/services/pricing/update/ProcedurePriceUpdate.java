package br.com.easyclinica.domain.services.pricing.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllProcedures;

@Component
public class ProcedurePriceUpdate {

	private final AllProcedures allProcedures;

	public ProcedurePriceUpdate(AllProcedures allProcedures) {
		this.allProcedures = allProcedures;
	}

	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> procedures) {

		Map<Integer, PrecifiedProcedure> map = generateMap(plan);

		for (ImportedStuff importedProcedure : procedures) {
			PrecifiedProcedure precifiedProcedure = map.get(importedProcedure
					.getId());

			if (precifiedProcedure != null) {
				precifiedProcedure.setFixedAmount(importedProcedure.getValue());
				precifiedProcedure.setCh(importedProcedure.getCh());
				precifiedProcedure.setRoomTaxAmount(importedProcedure
						.getRoomTax());
			} else {
				if (importedProcedure.getValue().doubleValue() == 0.0) {
					plan.addPrecifiedProcedure(
							allProcedures.getById(importedProcedure.getId()),
							importedProcedure.getCh(),
							importedProcedure.getRoomTax());
				} else {
					plan.addPrecifiedProcedure(
							allProcedures.getById(importedProcedure.getId()),
							importedProcedure.getValue(),
							importedProcedure.getRoomTax());
				}
			}
		}

	}

	private Map<Integer, PrecifiedProcedure> generateMap(HealthCarePlan plan) {
		Map<Integer, PrecifiedProcedure> map = new HashMap<Integer, PrecifiedProcedure>();
		for (PrecifiedProcedure pm : plan.getPrecifiedProcedures()) {
			map.put(pm.getProcedure().getId(), pm);
		}
		return map;
	}

}
