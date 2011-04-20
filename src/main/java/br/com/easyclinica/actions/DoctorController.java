package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.repositories.AllSpecialties;
import br.com.easyclinica.domain.validators.DoctorValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;
import br.com.easyclinica.view.paginator.Paginator;

@Resource
public class DoctorController extends BaseController {
	
	private final AllDoctors allDoctors;
	private final Validator validator;
	private final DoctorValidator doctorValidator;
	private final ErrorTranslator translator;
	private final Paginator paginator;
	private final AllSpecialties allSpecialties;
	
	public DoctorController(AllDoctors allDoctors, Result result, Validator validator, 
							DoctorValidator doctorValidator, AllSpecialties allSpecialties, ErrorTranslator translator, Paginator paginator) {
		super(result);
		this.allDoctors = allDoctors;
		this.validator = validator;
		this.doctorValidator = doctorValidator;
		this.translator = translator;
		this.paginator = paginator;
		this.allSpecialties = allSpecialties;
	}
	
	@Get
	@Path("/medicos")
	public void index(int page) {
		int currentPage = page == 0 ? Paginator.firstPage() : page;
		result.include("doctors", paginator.paginate(allDoctors,currentPage));
	}
	
	@Get
	@Path("/medicos/novo")
	public void newForm() {
		Doctor emptyDoctor = Doctor.empty();
		
		include(emptyDoctor);
		result.include("specialties", allSpecialties.getAll());
	}
	
	@Post
	@Path("/medicos")
	public void save(final Doctor doctor) {
		translator.translate(doctorValidator.validate(doctor));
		validator.onErrorUse(Results.logic()).forwardTo(DoctorController.class).newForm();
		
		allDoctors.add(doctor);
		
		successMsg(Messages.DOCTOR_ADDED);
		result.redirectTo(DoctorController.class).index(Paginator.firstPage());
	}

	@Get
	@Path("/medicos/{id}/editar")
	public void edit(int id) {
		Doctor doctorToBeEdited = allDoctors.getById(id);
		include(doctorToBeEdited);
		
		result.include("specialties", allSpecialties.getAll());
	}

	@Put
	@Path("medicos/{doctor.id}")
	public void update(final Doctor doctor) {
		translator.translate(doctorValidator.validate(doctor));
		validator.onErrorUse(Results.logic()).forwardTo(DoctorController.class).edit(doctor.getId());
		
		allDoctors.update(doctor);
		
		successMsg(Messages.DOCTOR_UPDATED);
		result.redirectTo(DoctorController.class).index(Paginator.firstPage());
	}

	@Post
	public void _show(int id) {
		Doctor loadedDoctor = allDoctors.getById(id);
		include(loadedDoctor);
	}
	
	@Put
	@Path("medicos/{id}/deactivate")
	public void deactivate(int id) {
		Doctor doctor = allDoctors.getById(id);
		
		doctor.deactivate();
		allDoctors.update(doctor);
		
		successMsg(Messages.DOCTOR_DEACTIVATED);
		result.redirectTo(DoctorController.class).index(Paginator.firstPage());
	}
	
	@Put
	@Path("medicos/{id}/activate")
	public void activate(int id) {
		Doctor doctor = allDoctors.getById(id);
		
		doctor.activate();
		allDoctors.update(doctor);
		
		successMsg(Messages.DOCTOR_ACTIVATED);
		result.redirectTo(DoctorController.class).index(Paginator.firstPage());
	}
	
	@Get
	@Path("medicos/{id}/especialidade")
	public void getSpecialty(int id) {
		Doctor doctor = allDoctors.getById(id);
		
		result.use(Results.json()).from(doctor.getSpecialty()).serialize();
	}
	
	private void include(Doctor emptyDoctor) {
		result.include("doctor", emptyDoctor);
	}
}