package br.com.easyclinica.actions;

import static br.com.caelum.vraptor.view.Results.page;
import static br.com.easyclinica.validators.matchers.NotEmpty.notEmpty;
import static org.hamcrest.Matchers.is;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;

@Resource
public class HealthCarePlanController {

	private final AllHealthCarePlans allHealthCares;
	private final Result result;
	private final Validator validator;

	public HealthCarePlanController(AllHealthCarePlans allHealthCares, Result result, Validator validator) {
		this.allHealthCares = allHealthCares;
		this.result = result;
		this.validator = validator;
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
		validator.checking(new Validations() {{
			that(healthCarePlan.getName().toString(), is(notEmpty()), "nome", "errors.invalid_name");
		}});
		validator.onErrorUse(page()).of(HealthCarePlanController.class).newForm();
		
		allHealthCares.add(healthCarePlan);
		result.redirectTo(HealthCarePlanController.class).index();
	}
}
