package br.com.easyclinica.domain.services.pricing;

import br.com.easyclinica.domain.entities.HealthCarePlan;

public interface PricingZerifier {
	void zeroPrices(HealthCarePlan healthCarePlan);
}
