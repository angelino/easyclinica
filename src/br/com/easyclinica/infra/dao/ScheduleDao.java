package br.com.easyclinica.infra.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.repositories.AllSchedule;

@Component
public class ScheduleDao implements AllSchedule {

	private final Session session;

	public ScheduleDao(Session session) {
		this.session = session;
	}
	
	public void add(Schedule schedule) {
		session.save(schedule);
	}

	public void update(Schedule schedule) {
		session.merge(schedule);
	}

	public void delete(Schedule schedule) {
		session.delete(schedule);
	}
	
}
