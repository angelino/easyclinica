package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

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

	@SuppressWarnings("unchecked")
	public List<HealthCarePlan> get(int firstResult, int maxResults) {
		Query query = session.createQuery("from HealthCarePlan hc order by name");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		return query.list();
	}

	public int count() {
		Criteria criteria = session.createCriteria(HealthCarePlan.class).setProjection(Projections.count("id"));
		return (Integer)criteria.uniqueResult();
	}
}
