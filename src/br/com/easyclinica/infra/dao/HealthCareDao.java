package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCare;
import br.com.easyclinica.domain.repositories.AllHealthCares;

@Component
public class HealthCareDao implements AllHealthCares {
	private final Session session;

	public HealthCareDao(Session session) {
		this.session = session;
	}
	
	public void add(HealthCare healthCare) {
		session.save(healthCare);
	}
	
	@SuppressWarnings("unchecked")
	public List<HealthCare> get() {
		return (List<HealthCare>)session.createQuery("from HealthCare hc order by name").list();
	}
}
