package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
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
	private final HealthCarePlanValidator healthCarePlanValidator;
	private final ErrorTranslator translator;

	public HealthCarePlanController(AllHealthCarePlans allHealthCares, Result result, Validator validator,
			HealthCarePlanValidator healthCarePlanValidator, ErrorTranslator translator) {
		this.allHealthCares = allHealthCares;
		this.result = result;
		this.validator = validator;
		this.healthCarePlanValidator = healthCarePlanValidator;
		this.translator = translator;
	}
	
	@Get
	@Path("/convenios")
	public void index() {
		result.include("healthcares", allHealthCares.get());
	}

	@Get
	@Path("/convenios/novo")
	public void newForm() {
		HealthCarePlan emptyPlan = HealthCarePlan.empty();
		include(emptyPlan);
	}
	
	@Post
	@Path("/convenios")
	public void save(final HealthCarePlan healthCarePlan) {
		translator.translate(healthCarePlanValidator.validate(healthCarePlan));
		validator.onErrorUse(Results.logic()).forwardTo(HealthCarePlanController.class).newForm();
		
		allHealthCares.add(healthCarePlan);
		
		result.include("message", Messages.HEALTH_CARE_PLAN_ADDED);
		result.redirectTo(HealthCarePlanController.class).index();
	}

	@Get
	@Path("/convenios/{id}/editar")
	public void edit(int id) {
		HealthCarePlan planToBeEdited = allHealthCares.getById(id);
		include(planToBeEdited);
	}

	@Put
	@Path("convenios/{healthCarePlan.id}")
	public void update(final HealthCarePlan healthCarePlan) {
		translator.translate(healthCarePlanValidator.validate(healthCarePlan));
		validator.onErrorUse(Results.logic()).forwardTo(HealthCarePlanController.class).edit(healthCarePlan.getId());
		
		allHealthCares.update(healthCarePlan);
		
		result.include("message", Messages.HEALTH_CARE_PLAN_UPDATED);
		result.redirectTo(HealthCarePlanController.class).index();
	}

	@Get
	@Path("convenios/{id}")
	public void show(int id) {
		HealthCarePlan loadedPlan = allHealthCares.getById(id);
		include(loadedPlan);
	}
	
	private void include(HealthCarePlan planToBeEdited) {
		result.include("healthCarePlan", planToBeEdited);
	}
}
