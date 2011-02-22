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
import br.com.easyclinica.domain.services.ConsultSchedule;

@Resource
public class ScheduleController extends BaseController {

	private AllDoctors allDoctors;
	private ConsultSchedule consultSchedule;
	
	public ScheduleController(Result result, AllDoctors allDoctors, ConsultSchedule consultSchedule) {
		super(result);
		
		this.allDoctors = allDoctors;
		this.consultSchedule = consultSchedule;
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
		
		List<Schedule> schedules = consultSchedule.ofTheDoctor(doctor)
												  .from(showdate)
												  .execute(viewtype);
		
		Calendar start = (Calendar)showdate.clone();
		Calendar end = (Calendar)showdate.clone();
		consultSchedule.setTime(start, 0, 0, 0);
		consultSchedule.setTime(end, 23, 59, 59);
		
		if(viewtype.equals(ConsultSchedule.VIEW_TYPE_WEEK)) {
			start = consultSchedule.getFirstDayOfWeek(start);
			end = consultSchedule.getLastDayOfWeek(end);
		} else if(viewtype.equals(ConsultSchedule.VIEW_TYPE_MONTH)) {
			start = consultSchedule.getFirstDayOfMonth(start);
			end = consultSchedule.getLastDayOfMonth(end);
		}
		
		result.include("start", start);
		result.include("end", end);
		result.include("schedules", schedules);
	}
}
