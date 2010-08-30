package br.com.easyclinica.actions;

import java.util.Date;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.domain.repositories.AllSchedule;
import br.com.easyclinica.domain.validators.ScheduleValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.services.GetDoctorSchedule;
import br.com.easyclinica.view.Messages;
import br.com.easyclinica.view.paginator.Paginator;

@Resource
public class ScheduleController extends BaseController {
	private final AllSchedule allSchedule;
	private final AllDoctors allDoctors;
	private final AllPatients allPatients; 
	private final Validator validator;
	private final ScheduleValidator scheduleValidator;
	private final ErrorTranslator translator;
	private final GetDoctorSchedule doctorSchedule;
	
	public ScheduleController(Result result, Validator validator, AllSchedule allSchedule,
							  ScheduleValidator scheduleValidator, ErrorTranslator translator,
							  AllDoctors allDoctors, GetDoctorSchedule doctorSchedule,
							  AllPatients allPatients) {
		super(result);
		this.validator = validator;
		this.allSchedule = allSchedule;
		this.scheduleValidator = scheduleValidator;
		this.translator = translator;
		this.allDoctors = allDoctors;
		this.doctorSchedule = doctorSchedule;
		this.allPatients = allPatients;
	}

	@Get
	@Path("/agenda/{id}")
	public void index(int id) 
	{
		Doctor doctor = allDoctors.getById(id);
				
		result.include("doctor", doctor);
		result.include("doctors", allDoctors.get());
		result.include("appointments", doctorSchedule.from(doctor).at(new Date()).build());
	}
	
	@Post
	@Path("/agenda")
	public void save(final Schedule schedule) {
		translator.translate(scheduleValidator.validate(schedule));
		validator.onErrorUse(Results.page()).of(ScheduleController.class).index(schedule.getDoctor().getId());
		
		allSchedule.add(schedule);
			
		successMsg(Messages.SCHEDULE_ADDED);
		
		result.redirectTo(ScheduleController.class).medicalAppointments(schedule.getDoctor().getId());
	}
	
	public void medicalAppointments(int doctor_id)
	{
		Doctor doctor = allDoctors.getById(doctor_id);
		result.include("appointments", doctorSchedule.from(doctor).at(new Date()).build());
	}
}
