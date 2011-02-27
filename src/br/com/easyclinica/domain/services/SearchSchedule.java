package br.com.easyclinica.domain.services;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.repositories.AllSchedule;

@Component
public class SearchSchedule {
	
	public static final String VIEW_TYPE_DAY = "day";
	public static final String VIEW_TYPE_WEEK = "week";
	public static final String VIEW_TYPE_MONTH = "month";
	
	private AllSchedule allSchedule;
	private Doctor doctor;
	private Calendar date;
	
	public SearchSchedule(AllSchedule allSchedule){
		this.allSchedule = allSchedule;
	}
	
	public SearchSchedule ofTheDoctor(Doctor doctor) {
		this.doctor = doctor;
		return this;
	}
	
	public SearchSchedule from(Calendar date) {
		this.date = date;
		return this;
	}
	
	public List<Schedule> execute(String viewType) {
		Calendar start = (Calendar)date.clone();
		Calendar end = (Calendar)date.clone();
		
		setTime(start, 0, 0, 0);
		setTime(end, 23, 59, 59);
		
		if(viewType.equals(VIEW_TYPE_WEEK)) {
			start = getFirstDayOfWeek(start);
			end = getLastDayOfWeek(end);			
		} else if(viewType.equals(VIEW_TYPE_MONTH)) {
			start = getFirstDayOfMonth(start);
			end = getLastDayOfMonth(end);			
		}
		
		return allSchedule.getDoctorScheduleByPeriod(doctor, start, end);
	}
	
	public void setTime(Calendar date, int hour, int minute, int second) {
		date.set(Calendar.HOUR_OF_DAY, hour);
		date.set(Calendar.MINUTE, minute);
		date.set(Calendar.SECOND, second);
	}
	
	public Calendar getFirstDayOfWeek(Calendar date) {
		Calendar first = (Calendar)date.clone();
		
		int firstDayOfTheWeek = date.getFirstDayOfWeek();
		int currentDayOfTheWeek = date.get(Calendar.DAY_OF_WEEK);
		int currentDayOfTheMonth = date.get(Calendar.DAY_OF_MONTH);
		
		if(currentDayOfTheWeek == Calendar.SUNDAY) currentDayOfTheWeek = 8;
		
		first.set(Calendar.DAY_OF_MONTH, currentDayOfTheMonth - (currentDayOfTheWeek - firstDayOfTheWeek));
		
		return first;
	}
	
	public Calendar getLastDayOfWeek(Calendar date) {
		Calendar end = (Calendar)date.clone();
		
		int lastDayOfWeek = date.getMaximum(Calendar.DAY_OF_WEEK);
		int currentDayOfTheWeek = date.get(Calendar.DAY_OF_WEEK);
		int currentDayOfTheMonth = date.get(Calendar.DAY_OF_MONTH);
		
		if(currentDayOfTheWeek == Calendar.SUNDAY) currentDayOfTheWeek = lastDayOfWeek;
		
		end.set(Calendar.DAY_OF_MONTH, currentDayOfTheMonth + (lastDayOfWeek - currentDayOfTheWeek));
		
		return end;
	}
	
	public Calendar getFirstDayOfMonth(Calendar date) {
		Calendar first = (Calendar)date.clone();		
		first.set(Calendar.DAY_OF_MONTH, 1);		
		return first;
	}
	
	public Calendar getLastDayOfMonth(Calendar date) {
		Calendar end = (Calendar)date.clone();
		
		int lastDayOfMonth = date.getMaximum(Calendar.DAY_OF_MONTH);
		end.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
		
		return end;
	}
}
