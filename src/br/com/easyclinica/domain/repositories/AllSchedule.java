package br.com.easyclinica.domain.repositories;

import br.com.easyclinica.domain.entities.Schedule;

public interface AllSchedule extends Pagging<Schedule> {	
	void add(Schedule schedule);
	void update(Schedule schedule);
}
