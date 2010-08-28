package br.com.easyclinica.infra.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.repositories.AllSchedule;

@Component
public class ScheduleDao implements AllSchedule {

	private final EntityManager em;
	
	public ScheduleDao(EntityManager em) {
		this.em = em;
	}
	
	public void add(Schedule schedule) {
		em.persist(schedule);		
	}

	public void update(Schedule schedule) {
		em.merge(schedule);
	}

	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Schedule> get(int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Schedule> get(Doctor doctor, Date date) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from Schedule schedule ");
		hql.append(" where ");
		hql.append(" schedule.doctor.id = :doctor_id ");
		hql.append(" and :date between schedule.start.hour and schedule.end.hour ");
		hql.append(" order by schedule.start ");
		
		Query query = em.createQuery(hql.toString());
		query.setParameter("doctor_id", doctor.getId());
		query.setParameter("date", date);
		return query.getResultList();
	}

}
