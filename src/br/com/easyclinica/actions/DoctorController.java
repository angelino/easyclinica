package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.validators.DoctorValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;

@Resource
public class DoctorController {
	
	private final AllDoctors allDoctors;
	private final Result result;
	private final Validator validator;
	private final DoctorValidator doctorValidator;
	private final ErrorTranslator translator;
	
	public DoctorController(AllDoctors allDoctors, Result result, Validator validator, 
							DoctorValidator doctorValidator, ErrorTranslator translator) {
		this.allDoctors = allDoctors;
		this.result = result;
		this.validator = validator;
		this.doctorValidator = doctorValidator;
		this.translator = translator;
	}
	
	@Get
	@Path("/medicos")
	public void index() {
		result.include("doctors", allDoctors.get());
	}
}
