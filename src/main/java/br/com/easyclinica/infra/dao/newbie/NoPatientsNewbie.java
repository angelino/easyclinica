package br.com.easyclinica.infra.dao.newbie;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.services.newbie.Newbie;

@Component
public class NoPatientsNewbie implements Newbie{

	private final Session session;
	
	public NoPatientsNewbie(Session session) {
		this.session = session;
	}
	
	public boolean isNewbie() {
		return ((Long)session.createCriteria(Patient.class).setProjection(Projections.rowCount()).uniqueResult()) == 0;
	}

}
