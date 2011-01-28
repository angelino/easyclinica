package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.domain.repositories.AllProcedures;
import br.com.easyclinica.domain.repositories.AllSpecialties;
import br.com.easyclinica.domain.repositories.PrecifiedThings;

@Resource
public class AppointmentsController extends BaseController {
	private final AllDoctors allDoctors;
	private final AllSpecialties allSpecialties;
	private final AllPatients allPatients;
	private final AllProcedures allProcedures;
	private final AllHealthCarePlans allHealthCarePlans;
	private final PrecifiedThings precifiedThings;
	
	public AppointmentsController(AllDoctors allDoctors, AllSpecialties allSpecialties, AllPatients allPatients, 
								AllProcedures allProcedures, AllHealthCarePlans allHealthCarePlans, 
								PrecifiedThings precifiedThings, Result result) {
		super(result);
		
		this.allDoctors = allDoctors;
		this.allSpecialties = allSpecialties;
		this.allPatients = allPatients;
		this.allProcedures = allProcedures;
		this.allHealthCarePlans = allHealthCarePlans;
		this.precifiedThings = precifiedThings;
	}

	@Get
	@Path("/pacientes/{patientId}/consultas/novo")
	public void newAppointment(int patientId) {
		Patient patient = allPatients.getById(patientId);
		
		result.include("patient", patient);
		result.include("doctors", allDoctors.getActivated());
		result.include("specialties", allSpecialties.getAll());
		result.include("patients", allPatients.getAll());
	}
	
	@Path("/teste")
	public void teste() {}
	
	@Post
	@Path("/pacientes/{appointment.patient.id}/consultas/novo")
	public void saveNewAppointment(Appointment appointment) {
		System.out.println(appointment);
	}
	
	@Post
	public void _newProcedureToAppointment(int procedureId, int convenioId) {
		 	Procedure procedure = allProcedures.getById(procedureId);
		 	HealthCarePlan healthCarePlan = allHealthCarePlans.getById(convenioId);
		 	
		 	PrecifiedProcedure precifiedProcedure = precifiedThings.getPrice(procedure, healthCarePlan);
		 	
		 	result.include("procedure", procedure);
		 	result.include("healthCarePlan", healthCarePlan);
		 	result.include("precifiedProcedure", precifiedProcedure);
	}
}
