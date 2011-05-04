package br.com.easyclinica.domain.services.pricing;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;

@Component
@RequestScoped
public class PricingUpdater {

	private final MedicinePriceUpdate medicineUpdater;
	private final MaterialPriceUpdate materialUpdater;
	private final SpecialtyPriceUpdate specialtyUpdater;
	private final ProcedurePriceUpdate procedureUpdater;

	public PricingUpdater(MaterialPriceUpdate materialUpdater,
			MedicinePriceUpdate medicineUpdater,
			ProcedurePriceUpdate procedureUpdater,
			SpecialtyPriceUpdate specialtyUpdater) {
		this.materialUpdater = materialUpdater;
		this.medicineUpdater = medicineUpdater;
		this.procedureUpdater = procedureUpdater;
		this.specialtyUpdater = specialtyUpdater;
	}

	public void pricesForAHealthCarePlan(HealthCarePlan plan,
			List<ImportedStuff> procedures, List<ImportedStuff> specialties,
			List<ImportedStuff> medicines, List<ImportedStuff> materials) {

		materialUpdater.pricesForAHealthCarePlan(plan, materials);
		medicineUpdater.pricesForAHealthCarePlan(plan, medicines);
		procedureUpdater.pricesForAHealthCarePlan(plan, procedures);
		specialtyUpdater.pricesForAHealthCarePlan(plan, specialties);
	}

}
