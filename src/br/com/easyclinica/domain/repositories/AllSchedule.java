package br.com.easyclinica.domain.repositories;

import java.util.Calendar;
import java.util.List;

import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;

public interface AllSchedule {
	void add(Schedule schedule);
	void update(Schedule schedule);
	void delete(Schedule schedule);
	
	List<Schedule> getDoctorScheduleByPeriod(Doctor doctor, Calendar start, Calendar end);
}