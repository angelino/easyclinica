package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.easyclinica.domain.entities.Appointment;

@Resource
public class AppointmentsController {

	@Get
	@Path("/pacientes/{patient}/consultas/novo")
	public void newAppointment(int patient) {
		
	}
	
	@Path("/teste")
	public void teste() {}
	
	@Post
	@Path("/pacientes/{appointment.patient.id}/consultas/novo")
	public void saveNewAppointment(Appointment appointment) {
		System.out.println(appointment);
	}
}
