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
import br.com.easyclinica.domain.services.pricing.PricingCopier;
import br.com.easyclinica.domain.validators.HealthCarePlanValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;
import br.com.easyclinica.view.paginator.Paginator;

@Resource
public class HealthCarePlanController extends BaseController {

	private final AllHealthCarePlans allHealthCares;
	private final Validator validator;
	private final HealthCarePlanValidator healthCarePlanValidator;
	private final ErrorTranslator translator;
	private final Paginator paginator;
	private final PricingCopier copier;

	public HealthCarePlanController(AllHealthCarePlans allHealthCares, 
			Result result, Validator validator, HealthCarePlanValidator healthCarePlanValidator, 
			ErrorTranslator translator, Paginator paginator, PricingCopier copier) {
		super(result);
		this.allHealthCares = allHealthCares;
		this.result = result;
		this.validator = validator;
		this.healthCarePlanValidator = healthCarePlanValidator;
		this.translator = translator;
		this.paginator = paginator;
		this.copier = copier;
	}
	
	@Get
	@Path("/convenios")
	public void index(int page) {
		int currentPage = page == 0 ? Paginator.firstPage() : page;
		result.include("healthcares", paginator.paginate(allHealthCares, currentPage));
	}

	@Get
	@Path("/convenios/novo")
	public void newForm() {
		HealthCarePlan emptyPlan = HealthCarePlan.empty();
		include(emptyPlan);
		
		result.include("healthCarePlans", allHealthCares.get());
	}
	
	@Post
	@Path("/convenios")
	public void save(final HealthCarePlan healthCarePlan, HealthCarePlan example) {
		translator.translate(healthCarePlanValidator.validate(healthCarePlan));
		validator.onErrorUse(Results.logic()).forwardTo(HealthCarePlanController.class).newForm();
		
		allHealthCares.add(healthCarePlan);
		copier.copyPrices(example, healthCarePlan);
		
		successMsg(Messages.HEALTH_CARE_PLAN_ADDED);
		result.redirectTo(HealthCarePlanController.class).index(Paginator.firstPage());
	}

	@Post
	public void _show(int id) {
		HealthCarePlan loadedPlan = allHealthCares.getById(id);
		include(loadedPlan);
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
		
		successMsg(Messages.HEALTH_CARE_PLAN_UPDATED);
		result.redirectTo(HealthCarePlanController.class).index(Paginator.firstPage());
	}

	private void include(HealthCarePlan plan) {
		result.include("healthCarePlan", plan);
	}

	@Put
	@Path("/convenios/{id}/deactivate")
	public void deactivate(int id) {
		HealthCarePlan plan = allHealthCares.getById(id);
		
		plan.deactivate();
		allHealthCares.update(plan);
		
		successMsg(Messages.HEALTH_CARE_PLAN_DEACTIVATED);
		result.redirectTo(HealthCarePlanController.class).index(Paginator.firstPage());
	}
	
	@Put
	@Path("/convenios/{id}/activate")
	public void activate(int id) {
		HealthCarePlan plan = allHealthCares.getById(id);
		
		plan.activate();
		allHealthCares.update(plan);
		
		successMsg(Messages.HEALTH_CARE_PLAN_ACTIVATED);
		result.redirectTo(HealthCarePlanController.class).index(Paginator.firstPage());
	}

	@Get
	@Path("/convenios/{planId}/resgatar")
	public void get(int planId) {
		HealthCarePlan plan = allHealthCares.getById(planId);
		
		result.use(Results.json()).from(plan).serialize();
	}
}
