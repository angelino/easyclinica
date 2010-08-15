package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.validators.DoctorValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.paginator.Paginator;

@Resource
public class DoctorController {
	
	private final AllDoctors allDoctors;
	private final Result result;
	private final Validator validator;
	private final DoctorValidator doctorValidator;
	private final ErrorTranslator translator;
	private final Paginator paginator;
	
	public DoctorController(AllDoctors allDoctors, Result result, Validator validator, 
							DoctorValidator doctorValidator, ErrorTranslator translator, Paginator paginator) {
		this.allDoctors = allDoctors;
		this.result = result;
		this.validator = validator;
		this.doctorValidator = doctorValidator;
		this.translator = translator;
		this.paginator = paginator;
	}
	
	@Get
	@Path("/medicos")
	public void index(int page) {
		int currentPage = page == 0 ? Paginator.firstPage() : page;
		result.include("doctors", paginator.paginate(allDoctors,currentPage));
	}
}
