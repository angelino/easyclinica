package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.repositories.AllPatientAppointments;

@Component
@RequestScoped
public class PatientAppointmentsDao implements AllPatientAppointments {

	private int patient;
	private final Session session;

	public PatientAppointmentsDao(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<Appointment> get(int firstResult, int maxResults) {
		return session.createCriteria(Appointment.class)
				.add(Restrictions.eq("patient.id", patient))
				.addOrder(Order.desc("appointmentDate")).setFirstResult(firstResult)
				.setMaxResults(maxResults).list();
	}

	public List<Appointment> search(String textToSearch, int firstResult,
			int maxResults) {
		throw new NotImplementedException();
	}

	public int count() {
		return ((Long) session.createCriteria(Appointment.class)
				.add(Restrictions.eq("patient.id", patient))
				.setProjection(Projections.count("id")).uniqueResult()).intValue();
	}

	public int count(String textToSearch) {
		throw new NotImplementedException();
	}

	public AllPatientAppointments forPatient(int id) {
		this.patient = id;
		return this;
	}

}
