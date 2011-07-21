package br.com.easyclinica.infra.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Prescription;
import br.com.easyclinica.domain.repositories.AllPrescriptions;

@Component
public class PrescriptionsDao implements AllPrescriptions {

	private final Session session;

	public PrescriptionsDao(Session session) {
		this.session = session;
	}
	
	public void add(Prescription prescription) {
		session.save(prescription);
	}

	public Prescription getById(int id) {
		return (Prescription) session.load(Prescription.class, id);
	}

	public void update(Prescription prescription) {
		session.merge(prescription);
	}

	public void delete(Prescription prescription) {
		session.delete(prescription);
	}

}
