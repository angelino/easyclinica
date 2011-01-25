package br.com.easyclinica.domain.repositories;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.Procedure;

public interface PrecifiedThings {
	PrecifiedProcedure getPrice(Procedure procedure, HealthCarePlan healthCarePlan);
}