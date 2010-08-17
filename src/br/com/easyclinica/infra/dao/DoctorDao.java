package br.com.easyclinica.infra.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.repositories.AllDoctors;

@Component
public class DoctorDao implements AllDoctors {

	private final EntityManager em;

	public DoctorDao(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Doctor> get() {
		return (List<Doctor>)em.createQuery("from Doctor doctors order by name").getResultList();
	}

	public void add(Doctor doctor) {
		em.persist(doctor);		
	}

	public Doctor getById(int id) {
		return (Doctor)em.find(Doctor.class, id);
	}

	public void update(Doctor doctor) {
		em.merge(doctor);
	}

	@SuppressWarnings("unchecked")
	public List<Doctor> get(int firstResult, int maxResults) {
		Query query = em.createQuery("from Doctor order by name");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		return query.getResultList();
	}

	public int count() {
		Query query = em.createQuery("select count(*) from Doctor");
		return ((Long)query.getSingleResult()).intValue();
	}
}
