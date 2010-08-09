package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;

@Resource
public class HealthCarePlanController {

	private final AllHealthCarePlans allHealthCares;
	private final Result result;

	public HealthCarePlanController(AllHealthCarePlans allHealthCares, Result result) {
		this.allHealthCares = allHealthCares;
		this.result = result;
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
	public void save(HealthCarePlan healthCarePlan) {
		allHealthCares.add(healthCarePlan);
		
		result.redirectTo(HealthCarePlanController.class).index();
	}
}
