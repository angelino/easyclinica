package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.repositories.AllDoctors;

@Resource
public class AppointmentsController extends BaseController {
	private final AllDoctors allDoctors;
	
	public AppointmentsController(AllDoctors allDoctors, Result result) {
		super(result);
		this.allDoctors = allDoctors;
	}

	@Get
	@Path("/pacientes/{patient}/consultas/novo")
	public void newAppointment(int patient) {
		
		result.include("doctors", allDoctors.getActivated());
	}
	
	@Path("/teste")
	public void teste() {}
	
	@Post
	@Path("/pacientes/{appointment.patient.id}/consultas/novo")
	public void saveNewAppointment(Appointment appointment) {
		System.out.println(appointment);
	}
	
	@Post
	public void _newProcedureToAppointment(String value, int convenioId) {
		Procedure procedure = new Procedure();
		
		procedure.setName(value);
		procedure.setAmbCode("00.000.00.0");
		procedure.setCh(20);
		
		Material material1 = new Material();
		material1.setName("Material 1");
		procedure.addMaterial(material1);
		
		Material material2 = new Material();
		material1.setName("Material 2");
		procedure.addMaterial(material2);
		
		Medicine medicine1 = new Medicine();
		medicine1.setName("Dorflex");
		procedure.addMedicine(medicine1);
		
		result.include("procedure", procedure);		
	}
}
