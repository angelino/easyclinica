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
import br.com.easyclinica.domain.validators.DoctorValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;
import br.com.easyclinica.view.paginator.Paginator;

@Resource
public class DoctorController {
	
	private final AllDoctors allDoctors;
	private final Result result;
	private final Validator validator;
	private final DoctorValidator doctorValidator;
	private final ErrorTranslator translator;
	private final Paginator paginator;
	
	public DoctorController(AllDoctors allDoctors, Result result, Validator validator, 
							DoctorValidator doctorValidator, ErrorTranslator translator, Paginator paginator) {
		this.allDoctors = allDoctors;
		this.result = result;
		this.validator = validator;
		this.doctorValidator = doctorValidator;
		this.translator = translator;
		this.paginator = paginator;
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
	}
	
	@Post
	@Path("/medicos")
	public void save(final Doctor doctor) {
		translator.translate(doctorValidator.validate(doctor));
		validator.onErrorUse(Results.logic()).forwardTo(DoctorController.class).newForm();
		
		allDoctors.add(doctor);
		
		result.include("message", Messages.DOCTOR_ADDED);
		result.redirectTo(DoctorController.class).index(Paginator.firstPage());
	}

	@Get
	@Path("/medicos/{id}/editar")
	public void edit(int id) {
		Doctor doctorToBeEdited = allDoctors.getById(id);
		include(doctorToBeEdited);
	}

	@Put
	@Path("convenios/{doctor.id}")
	public void update(final Doctor doctor) {
		translator.translate(doctorValidator.validate(doctor));
		validator.onErrorUse(Results.logic()).forwardTo(DoctorController.class).edit(doctor.getId());
		
		allDoctors.update(doctor);
		
		result.include("message", Messages.DOCTOR_UPDATED);
		result.redirectTo(DoctorController.class).index(Paginator.firstPage());
	}

	@Get
	@Path("medicos/{id}")
	public void show(int id) {
		Doctor loadedDoctor = allDoctors.getById(id);
		include(loadedDoctor);
	}
	
	private void include(Doctor emptyDoctor) {
		result.include("doctor", emptyDoctor);
	}
}