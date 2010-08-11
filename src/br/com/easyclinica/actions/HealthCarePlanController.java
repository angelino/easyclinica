package br.com.easyclinica.actions;

import static br.com.caelum.vraptor.view.Results.page;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.validators.HealthCarePlanValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;

@Resource
public class HealthCarePlanController {

	private final AllHealthCarePlans allHealthCares;
	private final Result result;
	private final Validator validator;
	private final HealthCarePlanValidator newHealthCarePlanValidator;
	private final ErrorTranslator translator;

	public HealthCarePlanController(AllHealthCarePlans allHealthCares, Result result, Validator validator,
			HealthCarePlanValidator newHealthCarePlanValidator, ErrorTranslator translator) {
		this.allHealthCares = allHealthCares;
		this.result = result;
		this.validator = validator;
		this.newHealthCarePlanValidator = newHealthCarePlanValidator;
		this.translator = translator;
	}
	
	@Get
	@Path("/convenios")
	public void index() {
		result.include("healthcares", allHealthCares.get());
	}

	@Get
	@Path("/convenios/novo")
	public void newForm() {	}
	
	@Post
	@Path("/convenios")
	public void save(final HealthCarePlan healthCarePlan) {
		translator.translate(newHealthCarePlanValidator.validate(healthCarePlan));
		validator.onErrorUse(Results.logic()).forwardTo(HealthCarePlanController.class).newForm();
		
		allHealthCares.add(healthCarePlan);
		
		result.include("message", Messages.HEALTH_CARE_PLAN_ADDED);
		result.redirectTo(HealthCarePlanController.class).index();
	}
}
