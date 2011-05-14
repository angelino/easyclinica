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
import br.com.easyclinica.domain.entities.GeneralObservations;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.repositories.AllGeneralObservations;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.domain.validators.GeneralObservationsValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;

@Resource
public class GeneralObservationsController extends BaseController {

	private final AllPatients patients;
	private final Validator validator;
	private final ErrorTranslator translator;
	private final GeneralObservationsValidator observationsValidator;
	private final AllGeneralObservations observations;
	
	public GeneralObservationsController(Result result, AllPatients patients, AllGeneralObservations observations, 
			Validator validator, ErrorTranslator translator, 
			GeneralObservationsValidator observationsValidator) {
		super(result);
		
		this.patients = patients;
		this.observations = observations;
		this.validator = validator;
		this.translator = translator;
		this.observationsValidator = observationsValidator;
	}

	@Get
	@Path("/pacientes/{patient}/observacoes")
	public Patient list(int patient) {
		return patients.getById(patient);
	}

	@Get
	@Path("/pacientes/{patient}/observacoes/{id}")
	public void show(int patient, int id) {
		GeneralObservations loadedObservation = observations.getById(id);
		result.include("patient", patients.getById(patient));
		include(loadedObservation);		
	}
	
	@Get
	@Path("/pacientes/{patient}/observacoes/novo")
	public void newForm(int patient) {
		GeneralObservations observation = GeneralObservations.empty();

		include(observation);
		result.include("patient", patients.getById(patient));
	}

	@Post
	@Path("/pacientes/{patient}/observacoes")
	public void save(int patient, final GeneralObservations observation) {
		translator.translate(observationsValidator.validate(observation));
		validator.onErrorUse(Results.logic())
				.forwardTo(GeneralObservationsController.class)
				.newForm(patient);

		observations.add(observation);

		successMsg(Messages.GENERAL_OBSERVATION_ADDED);
		result.redirectTo(GeneralObservationsController.class).show(patient, observation.getId());
	}

	@Get
	@Path("/pacientes/{patient}/observacoes/{id}/editar")
	public void edit(int patient, int id) {
		GeneralObservations observationToBeEdited = observations.getById(id);
		include(observationToBeEdited);

		result.include("patient", patients.getById(patient));
	}

	@Put
	@Path("/pacientes/{patient}/observacoes/{id}")
	public void update(int patient, final GeneralObservations observation) {
		translator.translate(observationsValidator.validate(observation));
		validator.onErrorUse(Results.logic())
				.forwardTo(GeneralObservationsController.class)
				.edit(patient, observation.getId());

		observations.update(observation);

		successMsg(Messages.GENERAL_OBSERVATION_UPDATED);
		result.redirectTo(GeneralObservationsController.class).list(patient);
	}

	@Delete
	@Path("/pacientes/{patient}/observacoes/{id}")
	public void delete(int patient, int id) {
		GeneralObservations observation = observations.getById(id);
		
		observations.delete(observation);
		
		successMsg(Messages.GENERAL_OBSERVATION_DELETED);
		result.redirectTo(GeneralObservationsController.class).list(patient);
	}
	
	private void include(GeneralObservations observation) {
		result.include("observation", observation);
	}
}
