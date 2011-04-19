package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.MaterialWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.Procedure;

public interface AllMaterials {
	List<MaterialWithPriceAndQuantity> getMaterialsWithPriceAndQuantity(Procedure procedure, HealthCarePlan healthCarePlan);

	List<Material> getAll();
	List<Material> search(String text);
	
	Material getById(int id);
}
