package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Service;
import br.com.easyclinica.domain.exceptions.InvalidServiceRuleException;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.view.Messages;
import br.com.easyclinica.view.paginator.Paginator;

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

	public void saveServiceRule(int id, Service service, CH ch, Money value) {
		HealthCarePlan plan = allHealthCares.getById(id);
		
		try {
			if(!ch.equals(CH.zero())) plan.addServiceRule(service, ch);
			else plan.addServiceRule(service, value);
			
			allHealthCares.update(plan);
			result.include("message", Messages.HEALTH_CARE_PLAN_SERVICE_RULE_ADDED);
		}
		catch(InvalidServiceRuleException e) {
			
		}
		
		result.forwardTo(HealthCarePlanController.class).index(Paginator.firstPage());
	}
}
