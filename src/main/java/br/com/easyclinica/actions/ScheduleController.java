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
import br.com.easyclinica.helper.CalendarUtils;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Resource
public class ScheduleController extends BaseController {

	private AllSchedule allSchedule;
	private AllDoctors allDoctors;
	private LoggedUser loggedUser;
	private CalendarUtils calendarUtils;
	
	public ScheduleController(Result result, AllSchedule allSchedule, AllDoctors allDoctors, LoggedUser loggedUser) {
		super(result);
		
		this.allSchedule = allSchedule;
		this.allDoctors = allDoctors;
		this.loggedUser = loggedUser;
	}
	
	@Get
	@Path("/agenda")
	public void index() {
		result.include("doctors", allDoctors.get());
		result.include("loggedDoctor", loggedDoctor());
	}
	
	@Get
	@Path("/agenda/{doctor.id}/{date}")
	public void loadAppointments(Doctor doctor, Calendar date) {
		Calendar start = calendarUtils.CloneDateAndSetTime(date, 0, 0, 0);
		Calendar end = calendarUtils.CloneDateAndSetTime(date, 23, 59, 59);
		
		List<Schedule> schedules = allSchedule.getDoctorScheduleByPeriod(doctor, start, end);
		
		result.include("schedules", schedules);
	}
	
	@Post
	@Path("/agenda/delete")
	public void delete(int scheduleId) {
		Schedule schedule = allSchedule.getById(scheduleId);
		
		allSchedule.delete(schedule);
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
