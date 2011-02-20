package br.com.easyclinica.domain.repositories;

import br.com.easyclinica.domain.entities.Schedule;

public interface AllSchedule {
	void add(Schedule schedule);
	void update(Schedule schedule);
	void delete(Schedule schedule);
}
