package br.com.easyclinica.actions;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.repositories.AllSchedule;
import br.com.easyclinica.domain.services.SearchSchedule;

@Resource
public class ScheduleController extends BaseController {

	private AllSchedule allSchedule;
	private AllDoctors allDoctors;
	private SearchSchedule searchSchedule;
	
	public ScheduleController(AllDoctors allDoctors, AllSchedule allSchedule ,
					SearchSchedule searchSchedule, Result result) {
		super(result);
		
		this.allSchedule = allSchedule;
		this.allDoctors = allDoctors;
		this.searchSchedule = searchSchedule;
	}

	
	@Get
	@Path("/medicos/{doctorId}/agenda")
	public void index(int doctorId) {
		Doctor doctor = allDoctors.getById(doctorId);
		
		result.include("doctor", doctor);
	}
	
	@Post
	@Path("/medicos/{doctorId}/agenda/_list")
	public void _list(int doctorId, Calendar showdate, String viewtype) {
		Doctor doctor = new Doctor(doctorId);
		
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
	@Path("/medicos/{doctorId}/agenda/_delete")
	public void _delete(int calendarId) {
		Schedule schedule = allSchedule.getById(calendarId);
		
		allSchedule.delete(schedule);
	}
	
	@Get
	@Path("/medicos/{doctorId}/agenda/_new")
	public void _new(int doctorId) {
		Schedule emptySchedule = Schedule.empty();		
		emptySchedule.setDoctor(new Doctor(doctorId));
		
		include(emptySchedule);
	}
	
	@Post
	@Path("/medicos/{schedule.doctor.id}/agenda/add")
	public void add(Schedule schedule) {
		allSchedule.add(schedule);
		
		result.redirectTo(ScheduleController.class).index(schedule.getDoctor().getId());
	}
	
	@Post
	@Path("/medicos/{schedule.doctor.id}/agenda/_quickAdd")
	public void _quickAdd(Schedule schedule) {
		allSchedule.add(schedule);
		
		include(schedule);
	}
	
	@Get
	@Path("/medicos/{doctorId}/agenda/{scheduleId}/_edit")
	public void _edit(int doctorId, int scheduleId) {
		Schedule schedule = allSchedule.getById(scheduleId);
		
		include(schedule);
	}
	
	@Post
	@Path("/medicos/{schedule.doctor.id}/agenda/update")
	public void update(Schedule schedule) {
		allSchedule.update(schedule);
		
		result.redirectTo(ScheduleController.class).index(schedule.getDoctor().getId());
	}
	
	@Post
	@Path("/medicos/{schedule.doctor.id}/agenda/_quickUpdate")
	public void _quickUpdate(int scheduleId, Calendar startTime, Calendar endTime) {
		Schedule schedule = allSchedule.getById(scheduleId);
		schedule.setStartTime(startTime);
		schedule.setEndTime(endTime);
		
		allSchedule.update(schedule);
	}
	
	private void include(Schedule schedule) {
		result.include("schedule", schedule);
	}
}
