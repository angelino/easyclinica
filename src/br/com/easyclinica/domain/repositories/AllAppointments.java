package br.com.easyclinica.domain.repositories;

import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.Specialty;

public interface AllAppointments {

	void save(Appointment appointment);
	Appointment getById(int id);
	Appointment getLastFromPatientAndSpecialty(Patient patient,	Specialty specialty);
}
