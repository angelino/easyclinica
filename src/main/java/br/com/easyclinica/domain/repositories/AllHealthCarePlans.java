package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.HealthCarePlan;

public interface AllHealthCarePlans extends Pagging<HealthCarePlan> {
	void add(HealthCarePlan healthCare);
	List<HealthCarePlan> get();
	List<HealthCarePlan> allActive();
	HealthCarePlan getById(int id);
	void update(HealthCarePlan plan);
}
