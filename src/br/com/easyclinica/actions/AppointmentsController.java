package br.com.easyclinica.actions;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.AppointmentMaterial;
import br.com.easyclinica.domain.entities.AppointmentMedicine;
import br.com.easyclinica.domain.entities.AppointmentProcedure;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.MaterialWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.MedicineWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.builders.MaterialWithPriceAndQuantityBuilder;
import br.com.easyclinica.domain.entities.builders.MedicineWithPriceAndQuantityBuilder;
import br.com.easyclinica.domain.repositories.AllAppointments;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.domain.repositories.AllProcedures;
import br.com.easyclinica.domain.repositories.AllSpecialties;
import br.com.easyclinica.domain.repositories.PrecifiedThings;
import br.com.easyclinica.domain.services.VerifyIfAnAppointmentIsReturnService;
import br.com.easyclinica.domain.validators.AppointmentValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;

@Resource
public class AppointmentsController extends BaseController {
	private final AllDoctors allDoctors;
	private final AllSpecialties allSpecialties;
	private final AllProcedures allProcedures;
	private final AllHealthCarePlans allHealthCarePlans;
	private final PrecifiedThings precifiedThings;
	private final AllAppointments allAppointments;
	private final AllPatients allPatients;
	private final MaterialWithPriceAndQuantityBuilder materialWithPriceAndQuantityBuilder;
	private final MedicineWithPriceAndQuantityBuilder medicineWithPriceAndQuantityBuilder;
	private final Validator validator;
	private final AppointmentValidator appointmentValidator;
	private final ErrorTranslator translator;
	private final VerifyIfAnAppointmentIsReturnService verifyIfAnAppointmentIsReturnService;
	
	public AppointmentsController(AllDoctors allDoctors, AllSpecialties allSpecialties, AllPatients allPatients, 
								AllProcedures allProcedures, AllHealthCarePlans allHealthCarePlans, 
								PrecifiedThings precifiedThings, AllAppointments allAppointments, 
								MaterialWithPriceAndQuantityBuilder materialWithPriceAndQuantityBuilder, 
								MedicineWithPriceAndQuantityBuilder medicineWithPriceAndQuantityBuilder, 
								Validator validator, AppointmentValidator appointmentValidator, ErrorTranslator translator,
								VerifyIfAnAppointmentIsReturnService verifyIfAnAppointmentIsReturnService,
								Result result) {

	
		super(result);
		
		this.allDoctors = allDoctors;

		this.allSpecialties = allSpecialties;
		this.allPatients = allPatients;
		this.allProcedures = allProcedures;
		this.allHealthCarePlans = allHealthCarePlans;
		this.precifiedThings = precifiedThings;
		this.allAppointments = allAppointments;
		this.materialWithPriceAndQuantityBuilder = materialWithPriceAndQuantityBuilder;
		this.medicineWithPriceAndQuantityBuilder = medicineWithPriceAndQuantityBuilder;
		this.validator = validator;
		this.appointmentValidator = appointmentValidator;
		this.translator = translator;
		this.verifyIfAnAppointmentIsReturnService = verifyIfAnAppointmentIsReturnService;
	}

	@Get
	@Path("/pacientes/{patientId}/consultas/novo")
	public void newAppointment(int patientId) {
		Patient patient = allPatients.getById(patientId);
		
		result.include("patient", patient);
		result.include("doctors", allDoctors.getActivated());
		result.include("specialties", allSpecialties.getAll());
	}
	
	@Get
	@Path("/pacientes/{patient}/consultas/{appointment}")
	public Appointment show(int patient, int appointment) {
		return allAppointments.getById(appointment);
	}
	
	@Get
	@Path("/pacientes/{patient}/consultas")
	public Patient list(int patient) {
		return allPatients.getById(patient);
	}
	
	private void setAllParents(Appointment appointment) {
		for (AppointmentProcedure procedure : appointment.getProcedures()) {
			procedure.setAppointment(appointment);

			for (AppointmentMaterial material : procedure.getMaterials()) {
				material.setProcedure(procedure);
			}

			for (AppointmentMedicine medicine : procedure.getMedicines()) {
				medicine.setProcedure(procedure);
			}
		}
	}

	
	@Post
	@Path("/pacientes/{appointment.patient.id}/consultas/novo")
	public void saveNewAppointment(Appointment appointment) {
		translator.translate(appointmentValidator.validate(appointment));
		validator.onErrorUse(Results.logic()).forwardTo(AppointmentsController.class).newAppointment(appointment.getPatient().getId());		
		
		setAllParents(appointment);
		appointment.recalculate();
		
		allAppointments.save(appointment);
		
		successMsg(Messages.APPOINTMENT_ADDED);
		result.redirectTo(AppointmentsController.class).show(appointment.getPatient().getId(), appointment.getId());
	}
	
	@Post
	public void _newProcedureToAppointment(int procedureId, int healthCarePlanId) {
	 	Procedure procedure = allProcedures.getById(procedureId);
	 	HealthCarePlan healthCarePlan = allHealthCarePlans.getById(healthCarePlanId);
	 	
	 	PrecifiedProcedure precifiedProcedure = precifiedThings.getPrice(procedure, healthCarePlan);
	 	List<MaterialWithPriceAndQuantity> materials = materialWithPriceAndQuantityBuilder.ofTheProcedure(procedure)
	 																					  .inTheHealthCarePlan(healthCarePlan)
	 																					  .execute();
	 	
	 	List<MedicineWithPriceAndQuantity> medicines = medicineWithPriceAndQuantityBuilder.ofTheProcedure(procedure)
																						  .inTheHealthCarePlan(healthCarePlan)
																						  .execute();
	 			 	
	 	result.include("procedure", procedure);
	 	result.include("healthCarePlan", healthCarePlan);
	 	result.include("precifiedProcedure", precifiedProcedure);
	 	result.include("materials", materials);
	 	result.include("medicines", medicines);
	}
	
	@Post
	public void isReturn(int patientId, int specialtyId, int healthCarePlanId, Calendar appointmentDate) {
		boolean isReturn = verifyIfAnAppointmentIsReturnService.check(patientId, specialtyId, healthCarePlanId, appointmentDate);
		
		result.use(Results.json()).from(isReturn).serialize();
	}
	
	@Delete
	@Path("/pacientes/{patientId}/consultas/{appointmentId}")
	public void delete(int patientId, int appointmentId) {
		Appointment appointment = allAppointments.getById(appointmentId);
		
		allAppointments.delete(appointment);
		
		successMsg(Messages.APPOINTMENT_DELETED);
		result.redirectTo(AppointmentsController.class).list(patientId);
	}
}
