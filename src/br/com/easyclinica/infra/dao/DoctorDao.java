package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.repositories.AllDoctors;

@Component
public class DoctorDao implements AllDoctors {

	private final Session session;

	public DoctorDao(Session session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	public List<Doctor> get() {
		return (List<Doctor>)session.createQuery("from Doctor doctors order by name").list();
	}

	public void add(Doctor doctor) {
		session.save(doctor);		
	}

	public Doctor getById(int id) {
		return (Doctor)session.load(Doctor.class, id);
	}

	public void update(Doctor doctor) {
		session.merge(doctor);
	}

}