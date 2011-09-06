package br.com.easyclinica.domain.repositories;

import br.com.easyclinica.domain.entities.Appointment;

public interface AllPatientAppointments extends Pagging<Appointment>{
	AllPatientAppointments forPatient(int id);
}
