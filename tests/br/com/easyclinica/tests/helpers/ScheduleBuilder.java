package br.com.easyclinica.tests.helpers;

import java.util.Calendar;

import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;

public class ScheduleBuilder {
	private Schedule schedule;
	
	public ScheduleBuilder() {
		this(0);
	}
	public ScheduleBuilder(int id) {
		schedule = new Schedule(id);
	}
	
	public ScheduleBuilder ofTheDoctor(Doctor doctor) {
		schedule.setDoctor(doctor);
		return this;
	}
	
	public ScheduleBuilder withStartTime(Calendar startTime) {
		schedule.setStartTime(startTime);
		return this;
	}
	
	public ScheduleBuilder withEndTime(Calendar endTime) {
		schedule.setEndTime(endTime);
		return this;
	}
	
	public ScheduleBuilder withSubject(String subject) {
		schedule.setSubject(subject);
		return this;
	}
	
	public Schedule instance() {
		return schedule;
	}
}
