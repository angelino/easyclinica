package br.com.easyclinica.domain.entities.builders;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.MaterialWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.repositories.AllMaterials;

@Component
public class MaterialWithPriceAndQuantityBuilder {

		private final AllMaterials allMaterial;
		
		private Procedure procedure;
		private HealthCarePlan healthCarePlan;
		
		public MaterialWithPriceAndQuantityBuilder(AllMaterials allMaterial) {
			this.allMaterial = allMaterial;
		}
	
		public MaterialWithPriceAndQuantityBuilder ofTheProcedure(Procedure procedure) {
			this.procedure = procedure;			
			return this;
		}
		
		public MaterialWithPriceAndQuantityBuilder inTheHealthCarePlan(HealthCarePlan healthCarePlan) {
			this.healthCarePlan = healthCarePlan;			
			return this;
		}
		
		public List<MaterialWithPriceAndQuantity> execute(){
			return allMaterial.getMaterialsWithPriceAndQuantity(procedure, healthCarePlan);
		}
}
