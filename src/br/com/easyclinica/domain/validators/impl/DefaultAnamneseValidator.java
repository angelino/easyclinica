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

	public List<Error> validate(Anamnese obj) {
		List<Error> errors = new ArrayList<Error>();
		
		if(ValidatorUtils.isNullOrEmpty(obj.getText())) {
			errors.add(new Error("anamnese", ValidationMessages.INVALID_ANAMNESE));
		}
		
		if(!ValidatorUtils.exists(obj.getDoctor())) {
			errors.add(new Error("doctor", ValidationMessages.INVALID_DOCTOR));
		}
		
		return errors;
	}

}
