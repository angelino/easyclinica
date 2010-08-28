package br.com.easyclinica.domain.repositories;

import java.util.Date;
import java.util.List;

import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;

public interface AllSchedule extends Pagging<Schedule> {	
	void add(Schedule schedule);
	void update(Schedule schedule);
	
	List<Schedule> get(Doctor doctor, Date date);
}
