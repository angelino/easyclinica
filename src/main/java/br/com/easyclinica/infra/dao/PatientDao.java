package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.repositories.AllPatients;

@Component
public class PatientDao implements AllPatients {

	private final Session session;

	public PatientDao(Session session) {
		this.session = session;
	}

	public void add(Patient patient) {
		session.save(patient);
	}

	public int count() {
		Query query = session.createQuery("select count(*) from Patient");
		return ((Long) query.uniqueResult()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<Patient> get(int firstResult, int maxResults) {
		Query query = session.createQuery("from Patient p order by name");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		return query.list();
	}

	public Patient getById(int id) {
		return (Patient)session.load(Patient.class, id);
	}

	public void update(Patient patient) {
		session.merge(patient);
	}

	@SuppressWarnings("unchecked")
	public List<Patient> getAll() {
		Query query = session.createQuery("from Patient p order by name");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Patient> search(String text) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from Patient patient ");
		hql.append(" where ");
		hql.append(" patient.cpf = :text ");
		hql.append(" or patient.email = :text ");
		hql.append(" or patient.name like :text_like ");
		hql.append(" order by name ");
		
		Query query = session.createQuery(hql.toString())
							 .setString("text", text)
							 .setString("text_like", "%" + text + "%");
				
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Patient> search(String textToSearch, int firstResult, int maxResults) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from Patient patient ");
		hql.append(" where ");
		hql.append(" patient.cpf = :text ");
		hql.append(" or patient.email = :text ");
		hql.append(" or patient.name like :text_like ");
		hql.append(" order by name ");
		
		Query query = session.createQuery(hql.toString())
							 .setString("text", textToSearch)
							 .setString("text_like", "%" + textToSearch + "%");
		
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		return query.list();
	}

	public int count(String textToSearch) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select count(*) from Patient patient ");
		hql.append(" where ");
		hql.append(" patient.cpf = :text ");
		hql.append(" or patient.email = :text ");
		hql.append(" or patient.name like :text_like ");
		
		Query query = session.createQuery(hql.toString())
							 .setString("text", textToSearch)
							 .setString("text_like", "%" + textToSearch + "%");
		
		return ((Long) query.uniqueResult()).intValue();
	}
		
}
