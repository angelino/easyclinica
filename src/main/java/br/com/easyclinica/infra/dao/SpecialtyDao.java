package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.domain.repositories.AllSpecialties;

@Component
public class SpecialtyDao implements AllSpecialties {

	private final Session session;

	public SpecialtyDao(Session session) {
		this.session = session;
	}
	
	public Specialty getById(int id) {
		return (Specialty)session.load(Specialty.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Specialty> getAll() {
		return (List<Specialty>)session.createQuery("from Specialty specialty order by name").list();
	}

	@SuppressWarnings("unchecked")
	public List<Specialty> get(int firstResult, int maxResults) {
		Query query = session.createQuery("from Specialty order by name");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		return query.list();
	}

	public int count() {
		Query query = session.createQuery("select count(*) from Specialty");
		return ((Long)query.uniqueResult()).intValue();
	}

	public List<Specialty> search(String textToSearch, int firstResult,
			int maxResults) {
		throw new RuntimeException("not implemented");
	}

	public int count(String textToSearch) {
		throw new RuntimeException("not implemented");
	}

}
