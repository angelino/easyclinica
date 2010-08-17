package br.com.easyclinica.infra.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;

@Component
public class HealthCarePlanDao implements AllHealthCarePlans {

	private final EntityManager em;

	public HealthCarePlanDao(EntityManager em) {
		this.em = em;
	}
	
	public void add(HealthCarePlan healthCare) {
		em.persist(healthCare);
	}
	
	@SuppressWarnings("unchecked")
	public List<HealthCarePlan> get() {
		return (List<HealthCarePlan>)em.createQuery("from HealthCarePlan hc order by name").getResultList();
	}

	public HealthCarePlan getById(int id) {
		return (HealthCarePlan)em.find(HealthCarePlan.class, id);
	}

	public void update(HealthCarePlan plan) {
		em.merge(plan);
	}

	@SuppressWarnings("unchecked")
	public List<HealthCarePlan> get(int firstResult, int maxResults) {
		Query query = em.createQuery("from HealthCarePlan hc order by name");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		return query.getResultList();
	}

	public int count() {
		Query query = em.createQuery("select count(*) from HealthCarePlan");
		return ((Long) query.getSingleResult()).intValue();
	}
}
