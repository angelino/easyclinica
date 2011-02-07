package br.com.easyclinica.infra.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.Specialty;
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

	public Appointment getLastFromPatientAndSpecialty(Patient patient, Specialty specialty) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from Appointment a ");
		sql.append(" where a.patient.id = :patientId ");
		sql.append(" and a.specialty.id = :specialtyId ");
		sql.append(" order by a.appointmentDate desc ");
		
		Query query = session.createQuery(sql.toString())
						.setParameter("patientId", patient.getId())
						.setParameter("specialtyId", specialty.getId())
						.setMaxResults(1);
		return (Appointment) query.uniqueResult();
	}

}
