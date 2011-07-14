package br.com.easyclinica.domain.services.pricing.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllSpecialties;

@Component
public class SpecialtyPriceUpdate {

	private final AllSpecialties allSpecialties;

	public SpecialtyPriceUpdate(AllSpecialties allSpecialties) {
		this.allSpecialties = allSpecialties;
	}

	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> specialties) {

		Map<Integer, PrecifiedSpecialty> map = generateMap(plan);

		for (ImportedStuff importedSpecialty : specialties) {
			PrecifiedSpecialty precifiedSpecialty = map.get(importedSpecialty
					.getId());
			if (precifiedSpecialty != null) {
				precifiedSpecialty.setAmount(importedSpecialty.getValue());
			} else {
				plan.addPrecifiedSpecialty(
						allSpecialties.getById(importedSpecialty.getId()),
						importedSpecialty.getValue());
			}
		}

	}

	private Map<Integer, PrecifiedSpecialty> generateMap(HealthCarePlan plan) {
		Map<Integer, PrecifiedSpecialty> map = new HashMap<Integer, PrecifiedSpecialty>();
		for (PrecifiedSpecialty pm : plan.getPrecifiedSpecialties()) {
			map.put(pm.getSpecialty().getId(), pm);
		}
		return map;
	}
}
