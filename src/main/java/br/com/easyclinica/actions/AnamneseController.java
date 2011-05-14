package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.easyclinica.domain.entities.Anamnese;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.repositories.AllAnamneses;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.domain.validators.AnamneseValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;

@Resource
public class AnamneseController extends BaseController {

	private final AllPatients patients;
	private final AllDoctors doctors;
	private final Validator validator;
	private final ErrorTranslator translator;
	private final AnamneseValidator anamneseValidator;
	private final AllAnamneses anamneses;

	public AnamneseController(AllPatients patients, AllDoctors doctors,
			AllAnamneses anamneses, Result result, Validator validator,
			ErrorTranslator translator, AnamneseValidator anamneseValidator) {

		super(result);

		this.patients = patients;
		this.doctors = doctors;
		this.anamneses = anamneses;
		this.validator = validator;
		this.translator = translator;
		this.anamneseValidator = anamneseValidator;
	}

	@Get
	@Path("/pacientes/{patient}/anamneses")
	public Patient list(int patient) {
		return patients.getById(patient);
	}

	@Get
	@Path("/pacientes/{patient}/anamneses/{id}")
	public void show(int patient, int id) {
		Anamnese loadedAnamnese = anamneses.getById(id);
		result.include("patient", patients.getById(patient));
		include(loadedAnamnese);		
	}
	
	@Get
	@Path("/pacientes/{patient}/anamneses/novo")
	public void newForm(int patient) {
		Anamnese emptyAnamnese = Anamnese.empty();

		include(emptyAnamnese);
		result.include("patient", patients.getById(patient));
		result.include("doctors", doctors.allActive());
	}

	@Post
	@Path("/pacientes/{patient}/anamneses")
	public void save(int patient, final Anamnese anamnese) {
		translator.translate(anamneseValidator.validate(anamnese));
		validator.onErrorUse(Results.logic())
				.forwardTo(AnamneseController.class)
				.newForm(anamnese.getPatient().getId());

		anamneses.add(anamnese);

		successMsg(Messages.ANAMNESE_ADDED);
		result.redirectTo(AnamneseController.class).show(patient, anamnese.getId());
	}

	@Get
	@Path("/pacientes/{patient}/anamneses/{id}/editar")
	public void edit(int patient, int id) {
		Anamnese anamneseToBeEdited = anamneses.getById(id);
		include(anamneseToBeEdited);

		result.include("patient", patients.getById(patient));
		result.include("doctors", doctors.get());
	}

	@Put
	@Path("/pacientes/{patient}/anamneses/{id}")
	public void update(int patient, final Anamnese anamnese) {
		translator.translate(anamneseValidator.validate(anamnese));
		validator.onErrorUse(Results.logic())
				.forwardTo(AnamneseController.class)
				.edit(patient, anamnese.getId());

		anamneses.update(anamnese);

		successMsg(Messages.ANAMNESE_UPDATED);
		result.redirectTo(AnamneseController.class).list(patient);
	}

	@Delete
	@Path("/pacientes/{patient}/anamneses/{id}")
	public void delete(int patient, int id) {
		Anamnese anamnese = anamneses.getById(id);
		
		anamneses.delete(anamnese);
		
		successMsg(Messages.ANAMNESE_DELETED);
		result.redirectTo(AnamneseController.class).list(patient);
	}
	
	private void include(Anamnese anamnese) {
		result.include("anamnese", anamnese);
	}

}
