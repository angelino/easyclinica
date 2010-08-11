package br.com.easyclinica.domain.validators.healthCarePlan;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ValidationKeys;
import br.com.easyclinica.domain.validators.Validator;

@Component
public class NewHealthCarePlanValidator implements Validator<HealthCarePlan> {
	
	public List<Error> validate(HealthCarePlan obj) {
		List<Error> errors = new ArrayList<Error>();
		if(obj.getName().toString() == null || obj.getName().toString().length() == 0) {
			errors.add(new Error("healthCarePlan", ValidationKeys.INVALID_NAME));
		}
		
		return errors;
	}

}
