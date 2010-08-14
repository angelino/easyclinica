package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.repositories.AllDoctors;

@Resource
public class DoctorController {
	
	private final AllDoctors allDoctors;
	private final Result result;
	
	public DoctorController(AllDoctors allDoctors, Result result) {
		this.allDoctors = allDoctors;
		this.result = result;
	}
	
	@Get
	@Path("/doctors")
	public void index() {
		result.include("doctors", allDoctors.get());
	}
}
