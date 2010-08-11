package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;

@Component
public class HealthCarePlanDao implements AllHealthCarePlans {
	private final Session session;

	public HealthCarePlanDao(Session session) {
		this.session = session;
	}
	
	public void add(HealthCarePlan healthCare) {
		session.save(healthCare);
	}
	
	@SuppressWarnings("unchecked")
	public List<HealthCarePlan> get() {
		return (List<HealthCarePlan>)session.createQuery("from HealthCarePlan hc order by name").list();
	}

	public HealthCarePlan getById(int id) {
		return (HealthCarePlan)session.load(HealthCarePlan.class, id);
	}

	public void update(HealthCarePlan plan) {
		session.merge(plan);
	}
}
