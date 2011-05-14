package br.com.easyclinica.domain.validators.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Anamnese;
import br.com.easyclinica.domain.validators.AnamneseValidator;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.domain.validators.ValidatorUtils;

@Component
public class DefaultAnamneseValidator implements AnamneseValidator {

	private final ValidatorUtils validatorUtils;
	
	public DefaultAnamneseValidator(ValidatorUtils validatorUtils){
		this.validatorUtils = validatorUtils;
	}
	
	public List<Error> validate(Anamnese obj) {
		List<Error> errors = new ArrayList<Error>();
		
		if(validatorUtils.isNullOrEmpty(obj.getComplaintAndDuration())) {
			errors.add(new Error("anamnese", ValidationMessages.INVALID_ANAMNESE));
		}
		
		if(!validatorUtils.exists(obj.getDoctor())) {
			errors.add(new Error("doctor", ValidationMessages.INVALID_DOCTOR));
		}
		
		return errors;
	}

}
