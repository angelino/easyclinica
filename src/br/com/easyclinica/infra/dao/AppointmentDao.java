package br.com.easyclinica.infra.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.repositories.AllAppointments;

@Component
public class AppointmentDao implements AllAppointments {

	private final Session session;

	public AppointmentDao(Session session) {
		this.session = session;
	}
	
	public void save(Appointment appointment) {
		session.save(appointment);
	}

	public Appointment getById(int id) {
		return (Appointment) session.load(Appointment.class, id);
	}

}
