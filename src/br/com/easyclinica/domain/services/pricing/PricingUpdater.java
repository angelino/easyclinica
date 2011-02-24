package br.com.easyclinica.domain.services.pricing;

import java.util.List;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;

public interface PricingUpdater {

	void pricesForAHealthCarePlan(HealthCarePlan plan, List<ImportedStuff> procedures,
			List<ImportedStuff> specialties, List<ImportedStuff> medicines,
			List<ImportedStuff> materials);
}
