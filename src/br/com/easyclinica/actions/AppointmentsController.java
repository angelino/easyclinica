package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.easyclinica.view.model.AppointmentData;

@Resource
public class AppointmentsController {

	@Get
	@Path("/pacientes/{patient}/consultas/novo")
	public void newAppointment(int patient) {
		
	}
	
	@Post
	@Path("/pacientes/{patient}/consultas/novo")
	public void saveNewAppointment(int patient, AppointmentData appointment) {
		
	}
}
