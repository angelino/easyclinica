package br.com.easyclinica.actions;

import static br.com.caelum.vraptor.view.Results.page;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.validators.healthCarePlan.NewHealthCarePlanValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;

@Resource
public class HealthCarePlanController {

	private final AllHealthCarePlans allHealthCares;
	private final Result result;
	private final Validator validator;
	private final NewHealthCarePlanValidator newHealthCarePlanValidator;
	private final ErrorTranslator translator;

	public HealthCarePlanController(AllHealthCarePlans allHealthCares, Result result, Validator validator,
			NewHealthCarePlanValidator newHealthCarePlanValidator, ErrorTranslator translator) {
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
	@Path("/convenios/novo")
	public void save(final HealthCarePlan healthCarePlan) {
		translator.translate(newHealthCarePlanValidator.validate(healthCarePlan));
		validator.onErrorUse(page()).of(HealthCarePlanController.class).newForm();
		
		allHealthCares.add(healthCarePlan);
		result.redirectTo(HealthCarePlanController.class).index();
	}
}
