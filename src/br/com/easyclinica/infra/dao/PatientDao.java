package br.com.easyclinica.infra.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.repositories.AllPatients;

@Component
public class PatientDao implements AllPatients {

	private final EntityManager em;

	public PatientDao(EntityManager em) {
		this.em = em;
	}

	public void add(Patient patient) {
		em.persist(patient);
	}

	public int count() {
		Query query = em.createQuery("select count(*) from Patient");
		return ((Long) query.getSingleResult()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<Patient> get(int firstResult, int maxResults) {
		Query query = em.createQuery("from Patient p order by name");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		return query.getResultList();
	}

	public Patient getById(int id) {
		return (Patient)em.find(Patient.class, id);
	}

	public void update(Patient patient) {
		em.merge(patient);
	}

	@SuppressWarnings("unchecked")
	public List<Patient> getAll() {
		Query query = em.createQuery("from Patient p order by name");
		return query.getResultList();
	}
}
