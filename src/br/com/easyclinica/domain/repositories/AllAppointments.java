package br.com.easyclinica.domain.repositories;

import br.com.easyclinica.domain.entities.Appointment;

public interface AllAppointments {

	void save(Appointment appointment);
	Appointment getById(int id);
}
