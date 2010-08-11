package br.com.easyclinica.domain.validators;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;

@Component
public class HealthCarePlanValidator implements EntityValidator<HealthCarePlan> {
	
	public List<Error> validate(HealthCarePlan obj) {
		List<Error> errors = new ArrayList<Error>();
		if(obj.getName().toString() == null || obj.getName().toString().length() == 0) {
			errors.add(new Error("healthCarePlan", ValidationMessages.INVALID_NAME));
		}
		
		return errors;
	}

}
