package br.com.easyclinica.actions;

import java.util.Calendar;

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

@Resource
public class ScheduleController extends BaseController {

	private AllSchedule allSchedule;
	private AllDoctors allDoctors;
	
	public ScheduleController(Result result, AllSchedule allSchedule, AllDoctors allDoctors) {
		super(result);
		
		this.allSchedule = allSchedule;
		this.allDoctors = allDoctors;
	}
	
	@Get
	@Path("/agenda")
	public void index() {
		result.forwardTo(MyScheduleController.class).index();
	}
	
	@Post
	@Path("/agenda/_list")
	public void _list(Calendar showdate, String viewtype) {
		result.forwardTo(MyScheduleController.class)._list(showdate, viewtype);
	}
	
	@Post
	@Path("/agenda/_delete")
	public void _delete(int calendarId) {
		result.forwardTo(MyScheduleController.class)._delete(calendarId);
	}
	
	@Get
	@Path("/agenda/_new")
	public void _new() {
		result.forwardTo(MyScheduleController.class)._new();
	}
	
	@Post
	@Path("/agenda/add")
	public void add(Schedule schedule) {
		Doctor doctor = allDoctors.getById(schedule.getDoctor().getId());
		schedule.setDoctor(doctor);
		
		allSchedule.add(schedule);
		
		result.redirectTo(ScheduleController.class).index();
	}
	
	@Get
	@Path("/agenda/{scheduleId}/_edit")
	public void _edit(int scheduleId) {
		result.forwardTo(MyScheduleController.class)._edit(scheduleId);
	}
	
	@Put
	@Path("/agenda/update")
	public void update(Schedule schedule) {
		Doctor doctor = allDoctors.getById(schedule.getDoctor().getId());
		schedule.setDoctor(doctor);
		
		allSchedule.update(schedule);
		
		result.redirectTo(ScheduleController.class).index();
	}
}
