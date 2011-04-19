package br.com.easyclinica.infra.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.repositories.ClinicInfo;

@Component
public class ClinicDao implements ClinicInfo {
	private final Session session;
	
	public ClinicDao(Session session) {
		this.session = session;
	}

	public Clinic get() {
		return (Clinic) session.createCriteria(Clinic.class).list().get(0);
	}

	public void update(Clinic clinic) {
		session.merge(clinic);
	}
	
	
	
}
