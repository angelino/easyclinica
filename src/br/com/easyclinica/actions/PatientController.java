package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.domain.validators.PatientValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;
import br.com.easyclinica.view.paginator.Paginator;

@Resource
public class PatientController extends BaseController {

	private final AllPatients allPatients;
	private final Paginator paginator;
	private final AllHealthCarePlans allHealthCarePlans;
	private final Validator validator;
	private final ErrorTranslator translator;
	private final PatientValidator patientValidator;

	public PatientController(AllPatients allPatients, AllHealthCarePlans allHealthCarePlans, Result result, Validator validator, PatientValidator patientValidator, ErrorTranslator translator, Paginator paginator) {
		super(result);
		
		this.allPatients = allPatients;
		this.allHealthCarePlans = allHealthCarePlans;
		this.validator = validator;
		this.patientValidator = patientValidator;
		this.translator = translator;
		this.paginator = paginator;
	}

	@Get
	@Path("/pacientes")
	public void index(int page) {
		int currentPage = page == 0 ? Paginator.firstPage() : page;
		result.include("patients", paginator.paginate(allPatients, currentPage));
	}

	@Get
	@Path("/pacientes/novo")
	public void newForm() {
		Patient emptyPatient = Patient.empty();
		result.include("healthCarePlans", allHealthCarePlans.get());
		include(emptyPatient);
	}

	@Get
	@Path("pacientes/{id}")
	public void show(int id) {
		Patient loadedPatient = allPatients.getById(id);
		include(loadedPatient);
	}

	@Put
	@Path("pacientes/{patient.id}")
	public void update(final Patient patient) {
		translator.translate(patientValidator.validate(patient));
		validator.onErrorUse(Results.logic()).forwardTo(PatientController.class).edit(patient.getId());
		
		allPatients.update(patient);
		
		successMsg(Messages.PATIENT_UPDATED);
		result.redirectTo(PatientController.class).index(Paginator.firstPage());
	}

	@Get
	@Path("/pacientes/{id}/editar")
	public void edit(int id) {
		Patient patientToBeEdited = allPatients.getById(id);
		
		result.include("healthCarePlans", allHealthCarePlans.get());
		include(patientToBeEdited);
	}

	@Post
	@Path("/pacientes")
	public void save(final Patient patient) {
		translator.translate(patientValidator.validate(patient));
		validator.onErrorUse(Results.logic()).forwardTo(PatientController.class).newForm();
		
		allPatients.add(patient);
		
		successMsg(Messages.PATIENT_ADDED);
		result.redirectTo(PatientController.class).index(Paginator.firstPage());
		
	}
	
	private void include(Patient patient) {
		result.include("patient", patient);
	}

}