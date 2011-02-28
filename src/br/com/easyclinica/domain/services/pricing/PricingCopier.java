package br.com.easyclinica.domain.services.pricing;

import br.com.easyclinica.domain.entities.HealthCarePlan;

public interface PricingCopier {

	void copyPrices(HealthCarePlan from, HealthCarePlan to);
}
