package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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
		text = text.replace(' ', '%');
		
		return session.createCriteria(Patient.class).add(Restrictions.disjunction().add(Restrictions.eq("cpf",text))
																				   .add(Restrictions.like("email", text))
																				   .add(Restrictions.like("name", text))
														)
													.addOrder(Order.asc("name"))
													.list();
	}

	@SuppressWarnings("unchecked")
	public List<Patient> search(String textToSearch, int firstResult, int maxResults) {
		textToSearch = textToSearch.replace(' ', '%');
		
		return session.createCriteria(Patient.class).add(Restrictions.disjunction().add(Restrictions.eq("cpf",textToSearch))
																				   .add(Restrictions.like("email", textToSearch))
																				   .add(Restrictions.like("name", textToSearch))
														)
														.addOrder(Order.asc("name"))
														.setFirstResult(firstResult)
														.setMaxResults(maxResults)
														.list();
	}

	public int count(String textToSearch) {
		textToSearch = textToSearch.replace(' ', '%');
		
		return ((Long) session.createCriteria(Patient.class).add(Restrictions.disjunction().add(Restrictions.eq("cpf",textToSearch))
																		    .add(Restrictions.like("email", textToSearch))
																		    .add(Restrictions.like("name", textToSearch))
														   	 )
														   	 .setProjection(Projections.count("id"))
															 .uniqueResult()).intValue();
	}
		
}
