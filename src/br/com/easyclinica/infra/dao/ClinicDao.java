package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.repositories.AllClinics;

@Component
public class ClinicDao implements AllClinics {
	private final Session session;
	
	public ClinicDao(Session session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	public List<Clinic> get(int firstResult, int maxResults) {
		Query query = session.createQuery("from Clinic order by name");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		return query.list();
	}

	public int count() {
		Query query = session.createQuery("select count(1) from Clinic");
		return ((Long)query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Clinic> get() {
		return (List<Clinic>)session.createQuery("from Clinic clinics order by name").list();
	}

	public Clinic getById(int id) {
		return (Clinic)session.load(Clinic.class, id);
	}

	public void add(Clinic clinic) {
		session.save(clinic);	
	}

	public void update(Clinic clinic) {
		session.merge(clinic);
	}

}
