package br.com.easyclinica.actions;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.repositories.AllSchedule;
import br.com.easyclinica.domain.services.SearchSchedule;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Resource
public class MyScheduleController extends BaseController {

	private AllSchedule allSchedule;
	private AllDoctors allDoctors;
	private SearchSchedule searchSchedule;
	private LoggedUser loggedUser;
	
	public MyScheduleController(Result result, SearchSchedule searchSchedule, AllDoctors allDoctors, 
								AllSchedule allSchedule, LoggedUser loggedUser) {
		super(result);
		
		this.allSchedule = allSchedule;
		this.allDoctors = allDoctors;
		this.searchSchedule = searchSchedule;
		this.loggedUser = loggedUser;
	}

	@Get
	@Path("/medicos/minha-agenda")
	public void index() {
		Doctor doctor = loggedDoctor();
		
		result.include("doctor", doctor);
	}

	@Post
	@Path("/medicos/minha-agenda/_list")
	public void _list(Calendar showdate, String viewtype) {
		Doctor doctor = loggedUser.getEmployee().getDoctor();
		
		List<Schedule> schedules = searchSchedule.ofTheDoctor(doctor)
												  .from(showdate)
												  .execute(viewtype);
		
		Calendar start = (Calendar)showdate.clone();
		Calendar end = (Calendar)showdate.clone();
		searchSchedule.setTime(start, 0, 0, 0);
		searchSchedule.setTime(end, 23, 59, 59);
		
		if(viewtype.equals(SearchSchedule.VIEW_TYPE_WEEK)) {
			start = searchSchedule.getFirstDayOfWeek(start);
			end = searchSchedule.getLastDayOfWeek(end);
		} else if(viewtype.equals(SearchSchedule.VIEW_TYPE_MONTH)) {
			start = searchSchedule.getFirstDayOfMonth(start);
			end = searchSchedule.getLastDayOfMonth(end);
		}
		
		result.include("start", start);
		result.include("end", end);
		result.include("schedules", schedules);
	}
	
	@Post
	@Path("/medicos/minha-agenda/_delete")
	public void _delete(int calendarId) {
		Schedule schedule = allSchedule.getById(calendarId);
		
		allSchedule.delete(schedule);
	}
	
	@Get
	@Path("/medicos/minha-agenda/_new")
	public void _new() {
		Doctor doctor = loggedDoctor();
		
		Schedule emptySchedule = Schedule.empty();		
		emptySchedule.setDoctor(doctor);
		
		List<Doctor> medicos = allDoctors.getActivated();
		result.include("medicos", medicos);
		
		include(emptySchedule);
	}
	
	@Post
	@Path("/medicos/minha-agenda/add")
	public void add(Schedule schedule) {
		schedule.setDoctor(loggedDoctor());
		
		allSchedule.add(schedule);
		
		result.redirectTo(MyScheduleController.class).index();
	}
	
	@Post
	@Path("/medicos/minha-agenda/_quickAdd")
	public void _quickAdd(Schedule schedule) {
		schedule.setDoctor(loggedUser.getEmployee().getDoctor());
		
		allSchedule.add(schedule);
		
		include(schedule);
	}
	
	@Get
	@Path("/medicos/minha-agenda/{scheduleId}/_edit")
	public void _edit(int scheduleId) {
		Schedule schedule = allSchedule.getById(scheduleId);
		
		List<Doctor> medicos = allDoctors.getActivated();
		result.include("medicos", medicos);
		
		include(schedule);
	}
	
	@Put
	@Path("/medicos/minha-agenda/update")
	public void update(Schedule schedule) {
		schedule.setDoctor(loggedDoctor());
		
		allSchedule.update(schedule);
		
		result.redirectTo(MyScheduleController.class).index();
	}
	
	@Post
	@Path("/medicos/minha-agenda/_quickUpdate")
	public void _quickUpdate(int scheduleId, Calendar startTime, Calendar endTime) {
		Schedule schedule = allSchedule.getById(scheduleId);
		schedule.setDoctor(loggedUser.getEmployee().getDoctor());
		schedule.setStartTime(startTime);
		schedule.setEndTime(endTime);
		
		allSchedule.update(schedule);
	}
	
	private Doctor loggedDoctor() {
		Doctor doctor = loggedUser.getEmployee().getDoctor();
		if(doctor == null) doctor = Doctor.empty();
		return doctor;
	}
	
	private void include(Schedule schedule) {
		result.include("schedule", schedule);
	}
}
