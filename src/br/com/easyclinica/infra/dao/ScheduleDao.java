package br.com.easyclinica.infra.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
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

}
