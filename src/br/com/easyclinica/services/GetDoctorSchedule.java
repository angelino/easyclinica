package br.com.easyclinica.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.config.Config;
import br.com.easyclinica.config.ConfigKeys;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.repositories.AllSchedule;

@Component
public class GetDoctorSchedule {
	private final AllSchedule allSchedule;
	private final Config config;
	private Doctor doctor;
	private Date date;
	
	public GetDoctorSchedule(AllSchedule allSchedule, Config config) {
		this.allSchedule = allSchedule;
		this.config = config;
	}
	
	public GetDoctorSchedule from(Doctor doctor) {
		this.doctor = doctor;
		return this;
	}
	
	public GetDoctorSchedule at(Date date) {
		this.date = date;
		return this;
	}
	
	public List<Schedule> build() {
		
		Calendar clinicStartOperation = (Calendar) ((Calendar)config.get(ConfigKeys.CLINIC_START_OPERATION)).clone();
		Calendar clinicEndOperation = (Calendar) ((Calendar)config.get(ConfigKeys.CLINIC_END_OPERATION)).clone();
		int medicalTime = Integer.parseInt(config.get(ConfigKeys.MEDICAL_TIME).toString());
		
		List<Schedule> appointments = allSchedule.get(this.doctor, this.date);
		
		List<Schedule> scheduleOfThisDate = new LinkedList<Schedule>();
		for(Calendar timeToBeAnalysed = clinicStartOperation; timeToBeAnalysed.before(clinicEndOperation); timeToBeAnalysed.add(Calendar.MINUTE, medicalTime))
		{
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
			
			Calendar appointmentEnd = (Calendar) timeToBeAnalysed.clone();
			appointmentEnd.add(Calendar.MINUTE, medicalTime);
			Schedule schedule = new Schedule(this.date, timeFormat.format(timeToBeAnalysed.getTime()), timeFormat.format(appointmentEnd.getTime()));
						
			for(Schedule s : appointments) {
				if(s.getFullStartHour().equals(timeToBeAnalysed.getTime())) {
					schedule = s;
					break;
				}
			}
			
			schedule.setAvailable((schedule.getFullStartHour().after(new Date()) && schedule.getId() == 0));
			
			scheduleOfThisDate.add(schedule);
		}
		
		return scheduleOfThisDate;
	}
}
