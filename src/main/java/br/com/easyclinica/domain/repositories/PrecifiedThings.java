package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.Specialty;

public interface PrecifiedThings {
	PrecifiedProcedure getPrice(Procedure procedure, HealthCarePlan healthCarePlan);
	List<PrecifiedMaterial> getMaterialsPrice(Procedure procedure, HealthCarePlan healthCarePlan);
	List<PrecifiedMedicine> getMedicinePrice(Procedure procedure, HealthCarePlan healthCarePlan);
	PrecifiedSpecialty getMedicalAppointmentPrice(Specialty specialty, HealthCarePlan healthCarePlan);
}