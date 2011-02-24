package br.com.easyclinica.domain.services.pricing;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;

@Component
public class DefaultPricingUpdater implements PricingUpdater {

	private final MedicinePriceUpdate medicineUpdater;
	private final MaterialPriceUpdate materialUpdater;
	private final AllHealthCarePlans plans;
	private final SpecialtyPriceUpdate specialtyUpdater;
	private final ProcedurePriceUpdate procedureUpdater;

	public DefaultPricingUpdater(MaterialPriceUpdate materialUpdater,
			MedicinePriceUpdate medicineUpdater,
			ProcedurePriceUpdate procedureUpdater,
			SpecialtyPriceUpdate specialtyUpdater, AllHealthCarePlans plans) {
		this.materialUpdater = materialUpdater;
		this.medicineUpdater = medicineUpdater;
		this.procedureUpdater = procedureUpdater;
		this.specialtyUpdater = specialtyUpdater;
		this.plans = plans;
	}

	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> procedures, List<ImportedStuff> specialties,
			List<ImportedStuff> medicines, List<ImportedStuff> materials) {

		materialUpdater.pricesForAHealthCarePlan(plan, materials);
		medicineUpdater.pricesForAHealthCarePlan(plan, medicines);
		procedureUpdater.pricesForAHealthCarePlan(plan, procedures);
		specialtyUpdater.pricesForAHealthCarePlan(plan, specialties);

		plans.updatePrices(plan);
	}

}
