package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.dto.MaterialWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Procedure;

public interface AllMaterials {
	List<MaterialWithPriceAndQuantity> getMaterialsWithPriceAndQuantity(Procedure procedure, HealthCarePlan healthCarePlan);
}