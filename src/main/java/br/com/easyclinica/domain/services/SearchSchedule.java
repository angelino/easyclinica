package br.com.easyclinica.domain.services;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.repositories.AllSchedule;
import br.com.easyclinica.helper.CalendarUtils;

@Component
public class SearchSchedule {
	
	private CalendarUtils calendarUtils;
	private AllSchedule allSchedule;
	private Doctor doctor;
	private Calendar date;
	
	public SearchSchedule(AllSchedule allSchedule, CalendarUtils calendarUtils){
		this.allSchedule = allSchedule;
		this.calendarUtils = calendarUtils;
	}
	
	public SearchSchedule ofTheDoctor(Doctor doctor) {
		this.doctor = doctor;
		return this;
	}
	
	public SearchSchedule from(Calendar date) {
		this.date = date;
		return this;
	}
	
	public List<Schedule> execute() {
		List<Schedule> schedules = null;
		
		Calendar start = calendarUtils.CloneDateAndSetTime(date, 0, 0, 0);
		Calendar end = calendarUtils.CloneDateAndSetTime(date, 23, 59, 59);
		
		if(this.doctor == null) schedules = allSchedule.getScheduleByPeriod(start, end);
		else schedules = allSchedule.getDoctorScheduleByPeriod(doctor, start, end);
		
		return schedules;
	}
}
