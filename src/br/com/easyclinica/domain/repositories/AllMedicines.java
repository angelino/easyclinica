package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.MedicineWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.Procedure;

public interface AllMedicines {
	List<MedicineWithPriceAndQuantity> getMedicinesWithPriceAndQuantity(Procedure procedure, HealthCarePlan healthCarePlan);
}
