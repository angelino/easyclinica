package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class AppointmentsController {

	@Get
	@Path("/pacientes/{patient}/consultas/novo")
	public void newAppointment(int patient) {
		
	}
}
