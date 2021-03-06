package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.repositories.AllDoctors;

@Component
public class DoctorDao implements AllDoctors {

	private final Session session;

	public DoctorDao(Session session) {
		this.session = session;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Doctor> get() {
		return (List<Doctor>)session.createQuery("from Doctor doctors order by active desc, name").list();
	}

	@SuppressWarnings("unchecked")
	public List<Doctor> allActive() {
		return (List<Doctor>)session.createQuery("from Doctor doctors where doctors.active = 1 order by name").list();
	}
	
	public void add(Doctor doctor) {
		session.save(doctor);		
	}

	public Doctor getById(int id) {
		return (Doctor)session.load(Doctor.class, id);
	}

	public void update(Doctor doctor) {
		session.merge(doctor);
	}

	@SuppressWarnings("unchecked")
	public List<Doctor> get(int firstResult, int maxResults) {
		Query query = session.createQuery("from Doctor order by active desc, name");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		return query.list();
	}

	public int count() {
		Query query = session.createQuery("select count(*) from Doctor");
		return ((Long)query.uniqueResult()).intValue();
	}


	@SuppressWarnings("unchecked")
	public List<Doctor> search(String textToSearch, int firstResult,
			int maxResults) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from Doctor doctor ");
		hql.append(" where ");
		hql.append(" doctor.crm = :text ");
		hql.append(" or doctor.name like :text_like ");
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
		hql.append(" select count(*) from Doctor doctor ");
		hql.append(" where ");
		hql.append(" doctor.crm = :text ");
		hql.append(" or doctor.name like :text_like ");
		
		Query query = session.createQuery(hql.toString())
							 .setString("text", textToSearch)
							 .setString("text_like", "%" + textToSearch + "%");
		
		return ((Long) query.uniqueResult()).intValue();
	}
}
