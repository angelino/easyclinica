package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.repositories.AllDoctors;

@Resource
public class ScheduleController extends BaseController {

	private AllDoctors allDoctors;
	
	public ScheduleController(Result result, AllDoctors allDoctors) {
		super(result);
		
		this.allDoctors = allDoctors;
	}

	
	@Get
	@Path("/medicos/{doctorId}/agenda")
	public void index(int doctorId) {
		Doctor doctor = allDoctors.getById(doctorId);
		
		result.include("doctor", doctor);
	}
}
