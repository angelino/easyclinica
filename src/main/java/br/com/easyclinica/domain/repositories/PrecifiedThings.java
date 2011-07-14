package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.domain.entities.pricing.PricedProcedure;
import br.com.easyclinica.domain.entities.pricing.PricedStuff;

public interface PrecifiedThings {
	PrecifiedProcedure getPrice(Procedure procedure, HealthCarePlan healthCarePlan);
	List<PrecifiedMaterial> getMaterialsPrice(Procedure procedure, HealthCarePlan healthCarePlan);
	List<PrecifiedMedicine> getMedicinePrice(Procedure procedure, HealthCarePlan healthCarePlan);
	PrecifiedSpecialty getMedicalAppointmentPrice(Specialty specialty, HealthCarePlan healthCarePlan);
	
	List<PricedStuff> getMaterialsPrice(HealthCarePlan plan);
	List<PricedStuff> getMedicinesPrice(HealthCarePlan plan);
	List<PricedStuff> getSpecialtiesPrice(HealthCarePlan plan);
	List<PricedProcedure> getProceduresPrice(HealthCarePlan plan);
}