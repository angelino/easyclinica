package br.com.easyclinica.actions;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.repositories.AllSchedule;
import br.com.easyclinica.helper.CalendarUtils;
import br.com.easyclinica.infra.multitenancy.LoggedUser;
import br.com.easyclinica.view.schedule.ScheduleUtils;

@Resource
public class ScheduleController extends BaseController {

	private AllSchedule allSchedule;
	private AllDoctors allDoctors;
	private LoggedUser loggedUser;
	private CalendarUtils calendarUtils;
	private ScheduleUtils scheduleUtils;
	
	public ScheduleController(Result result, AllSchedule allSchedule, AllDoctors allDoctors, 
							  LoggedUser loggedUser, CalendarUtils calendarUtils, ScheduleUtils scheduleUtils) {
		super(result);
		
		this.allSchedule = allSchedule;
		this.allDoctors = allDoctors;
		this.loggedUser = loggedUser;
		this.calendarUtils = calendarUtils;
		this.scheduleUtils = scheduleUtils;
	}
	
	@Get
	@Path("/agenda")
	public void index() {
		result.include("doctors", allDoctors.get());
		result.include("loggedDoctor", loggedDoctor());
	}
	
	@Get
	public void _loadAppointments(int doctorId, Calendar date) {
		Doctor doctor = new Doctor(doctorId);
		
		Calendar start = calendarUtils.CloneDateAndSetTime(date, 0, 0, 0);
		Calendar end = calendarUtils.CloneDateAndSetTime(date, 23, 59, 59);
		
		List<Schedule> schedules = allSchedule.getDoctorScheduleByPeriod(doctor, start, end);
		
		result.include("timeTable", scheduleUtils.buildDoctorSchedule(schedules));
	}
	
	@Put
	public void _changeArrivalTime(int scheduleId, Calendar arrivalTime) {
		Schedule schedule = allSchedule.getById(scheduleId);		
		schedule.setArrivalTime(arrivalTime);
		
		allSchedule.update(schedule);
		
		result.use(Results.json()).from("1", "status").serialize();
	}
	
	@Put
	public void _setAsNotTreated(int scheduleId) {
		Schedule schedule = allSchedule.getById(scheduleId);		
		schedule.markAsNotTreated();
		
		allSchedule.update(schedule);
		
		result.use(Results.json()).from("1", "status").serialize();
	}
	
	@Put
	public void _setAsTreated(int scheduleId) {
		Schedule schedule = allSchedule.getById(scheduleId);		
		schedule.markAsTreated();
		
		allSchedule.update(schedule);
		
		result.use(Results.json()).from("1", "status").serialize();
	}
	
	@Delete
	public void _delete(int scheduleId) {
		Schedule schedule = allSchedule.getById(scheduleId);
		
		allSchedule.delete(schedule);
		
		result.use(Results.json()).from("1", "status").serialize();
	}
	
	@Post
	@Path("/agenda")
	public void save(Schedule schedule) {
		Doctor doctor = allDoctors.getById(schedule.getDoctor().getId());
		schedule.setDoctor(doctor);
		
		allSchedule.add(schedule);
		
		result.redirectTo(ScheduleController.class).index();
	}
	
	private Doctor loggedDoctor() {
		Doctor doctor = loggedUser.getEmployee().getDoctor();
		if(doctor == null) doctor = Doctor.empty();
		return doctor;
	}
}
