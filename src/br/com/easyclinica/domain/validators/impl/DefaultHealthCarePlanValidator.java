package br.com.easyclinica.domain.validators.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.HealthCarePlanValidator;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.domain.validators.ValidatorUtils;

@Component
public class DefaultHealthCarePlanValidator implements HealthCarePlanValidator {
	
	public List<Error> validate(HealthCarePlan obj) {
		List<Error> errors = new ArrayList<Error>();
		if(ValidatorUtils.isNullOrEmpty(obj.getName())) {
			errors.add(new Error("healthCarePlan", ValidationMessages.INVALID_NAME));
		}
		if(!ValidatorUtils.isMoreThanZero(obj.getCh())) {
			errors.add(new Error("healthCarePlan", ValidationMessages.INVALID_CH));
		}
		
		return errors;
	}

}
