package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.HealthCarePlan;

public interface AllHealthCarePlans {
	void add(HealthCarePlan healthCare);
	List<HealthCarePlan> get();
}
