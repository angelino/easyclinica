package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.easyclinica.domain.repositories.AllSchedule;
import br.com.easyclinica.domain.validators.ScheduleValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;

@Resource
public class ScheduleController extends BaseController {
	private final AllSchedule allSchedule;
	private final Validator validator;
	private final ScheduleValidator scheduleValidator;
	private final ErrorTranslator translator;
	
	public ScheduleController(Result result, Validator validator, AllSchedule allSchedule,
							  ScheduleValidator scheduleValidator, ErrorTranslator translator) {
		super(result);
		this.validator = validator;
		this.allSchedule = allSchedule;
		this.scheduleValidator = scheduleValidator;
		this.translator = translator;
	}

	@Get
	@Path("/agenda")
	public void index() {
	}
}
