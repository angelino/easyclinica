package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;

@Resource
public class HealthCarePlanRuleController {
	private Result result;
	private AllHealthCarePlans allHealthCares;

	public HealthCarePlanRuleController(AllHealthCarePlans allHealthCares, Result result) {
		this.allHealthCares = allHealthCares;
		this.result = result;
	}
	
	public void index(int id) {
		
	}
}
