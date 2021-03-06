package br.com.easyclinica.actions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.view.Results;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.Prescription;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.domain.repositories.AllPrescriptions;
import br.com.easyclinica.domain.validators.PrescriptionsValidator;
import br.com.easyclinica.infra.multitenancy.LoggedUser;
import br.com.easyclinica.infra.reports.JasperMaker;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;

@Resource
public class PrescriptionsController extends BaseController {

	private final AllPatients patients;
	private final Validator validator;
	private final ErrorTranslator translator;
	private final PrescriptionsValidator prescriptionsValidator;
	private final AllPrescriptions prescriptions;
	private final AllDoctors doctors;
	private final JasperMaker jasperMaker;
	private final LoggedUser loggedUser;
	
	public PrescriptionsController(Result result, AllPatients patients, AllPrescriptions prescriptions, 
			AllDoctors doctors, Validator validator, ErrorTranslator translator, 
			PrescriptionsValidator prescriptionsValidator, JasperMaker jasperMaker, LoggedUser loggedUser) {
		super(result);
		
		this.patients = patients;
		this.prescriptions = prescriptions;
		this.doctors = doctors;
		this.validator = validator;
		this.translator = translator;
		this.prescriptionsValidator = prescriptionsValidator;
		this.jasperMaker = jasperMaker;
		this.loggedUser = loggedUser;
	}

	@Get
	@Path("/pacientes/{patient}/prescricoes")
	public Patient list(int patient) {
		return patients.getById(patient);
	}

	@Get
	@Path("/pacientes/{patient}/prescricoes/{id}")
	public void show(int patient, int id) {
		Prescription prescription = prescriptions.getById(id);
		result.include("doctors", doctors.get());
		result.include("patient", patients.getById(patient));
		include(prescription);		
	}
	
	@Get
	@Path("/pacientes/{patient}/prescricoes/novo")
	public void newForm(int patient) {
		Prescription prescription = new Prescription();

		include(prescription);
		result.include("doctors", doctors.get());
		result.include("patient", patients.getById(patient));
	}

	@Post
	@Path("/pacientes/{patient}/prescricoes")
	public void save(int patient, final Prescription prescription) {
		translator.translate(prescriptionsValidator.validate(prescription));
		validator.onErrorUse(Results.logic())
				.forwardTo(PrescriptionsController.class)
				.newForm(patient);

		prescriptions.add(prescription);

		successMsg(Messages.PRESCRIPTION_ADDED);
		result.redirectTo(PrescriptionsController.class).show(patient, prescription.getId());
	}

	@Get
	@Path("/pacientes/{patient}/prescricoes/{id}/editar")
	public void edit(int patient, int id) {
		Prescription prescriptionToBeEdited = prescriptions.getById(id);
		include(prescriptionToBeEdited);

		result.include("doctors", doctors.get());
		result.include("patient", patients.getById(patient));
	}

	@Put
	@Path("/pacientes/{patient}/prescricoes/{id}")
	public void update(int patient, final Prescription prescription) {
		translator.translate(prescriptionsValidator.validate(prescription));
		validator.onErrorUse(Results.logic())
				.forwardTo(PrescriptionsController.class)
				.edit(patient, prescription.getId());

		prescriptions.update(prescription);

		successMsg(Messages.PRESCRIPTION_UPDATED);
		result.redirectTo(PrescriptionsController.class).list(patient);
	}

	@Delete
	@Path("/pacientes/{patient}/prescricoes/{id}")
	public void delete(int patient, int id) {
		Prescription prescription = prescriptions.getById(id);
		
		prescriptions.delete(prescription);
		
		successMsg(Messages.PRESCRIPTION_DELETED);
		result.redirectTo(PrescriptionsController.class).list(patient);
	}
	
	@Get
	@Path("/pacientes/{patient_id}/prescricoes/{id}/imprimir")
	public Download prescription(int patient_id, int id) {
		Patient patient = patients.getById(patient_id);
		Prescription prescription = prescriptions.getById(id);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("prescription", prescription);
		params.put("clinic", loggedUser.getClinic());
		
		return jasperMaker.makePdf(  
	               "prescription",  
	               Collections.singletonList(patient),   
	               "prescricao.pdf",   
	               true,   
	               params);
	}	
	
	private void include(Prescription prescription) {
		result.include("prescription", prescription);
	}
}
