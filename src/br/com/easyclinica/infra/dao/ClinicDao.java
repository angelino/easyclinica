package br.com.easyclinica.infra.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.repositories.AllClinics;

@Component
public class ClinicDao implements AllClinics {
	private final EntityManager em;
	
	public ClinicDao(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Clinic> get(int firstResult, int maxResults) {
		Query query = em.createQuery("from Clinic order by name");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		return query.getResultList();
	}

	public int count() {
		Query query = em.createQuery("select count(1) from Clinic");
		return ((Long)query.getSingleResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Clinic> get() {
		return (List<Clinic>)em.createQuery("from Clinic clinics order by name").getResultList();
	}

	public Clinic getById(int id) {
		return (Clinic)em.find(Clinic.class, id);
	}

	public void add(Clinic clinic) {
		em.persist(clinic);	
	}

	public void update(Clinic clinic) {
		em.merge(clinic);
	}

}
