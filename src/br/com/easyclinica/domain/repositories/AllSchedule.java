package br.com.easyclinica.domain.repositories;

import java.util.Calendar;
import java.util.List;

import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;

public interface AllSchedule {
	Schedule getById(int id);
	void add(Schedule schedule);
	void update(Schedule schedule);
	void delete(Schedule schedule);
	
	List<Schedule> getDoctorScheduleByPeriod(Doctor doctor, Calendar start, Calendar end);
	List<Schedule> getScheduleByPeriod(Calendar start, Calendar end);
}
