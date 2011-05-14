package br.com.easyclinica.infra.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.GeneralObservations;
import br.com.easyclinica.domain.repositories.AllGeneralObservations;

@Component
public class GeneralObservationsDao implements AllGeneralObservations {

	private final Session session;

	public GeneralObservationsDao(Session session) {
		this.session = session;
	}
	
	public void add(GeneralObservations generalObservations) {
		session.save(generalObservations);
	}

	public GeneralObservations getById(int id) {
		return (GeneralObservations) session.load(GeneralObservations.class, id);
	}

	public void update(GeneralObservations generalObservations) {
		session.merge(generalObservations);
	}

	public void delete(GeneralObservations generalObservations) {
		session.delete(generalObservations);
	}

}
