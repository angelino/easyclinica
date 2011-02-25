package br.com.easyclinica.infra.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Doctor;
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

	@SuppressWarnings("unchecked")
	public List<Schedule> getDoctorScheduleByPeriod(Doctor doctor,
			Calendar start, Calendar end) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" from Schedule s ");
		sql.append(" where ");
		sql.append(" s.doctor.id = :doctorId ");
		sql.append(" and s.startTime between :start and :end ");
		
		return session.createQuery(sql.toString())
					  .setParameter("doctorId", doctor.getId())
					  .setParameter("start", start)
					  .setParameter("end", end)
					  .list();
	}

	public Schedule getById(int id) {
		return (Schedule)session.load(Schedule.class, id);
	}
	
}
