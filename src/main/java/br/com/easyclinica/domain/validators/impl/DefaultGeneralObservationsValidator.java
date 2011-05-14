package br.com.easyclinica.domain.validators.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.GeneralObservations;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.GeneralObservationsValidator;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.domain.validators.ValidatorUtils;

@Component
public class DefaultGeneralObservationsValidator  implements GeneralObservationsValidator {
	private final ValidatorUtils validatorUtils;
	
	public DefaultGeneralObservationsValidator(ValidatorUtils validatorUtils){
		this.validatorUtils = validatorUtils;
	}
	
	public List<Error> validate(GeneralObservations obj) {
		List<Error> errors = new ArrayList<Error>();
		
		if(validatorUtils.isNullOrEmpty(obj.getText())) {
			errors.add(new Error("observations", ValidationMessages.INVALID_GENERAL_OBSERVATIONS));
		}
		
		if(!validatorUtils.exists(obj.getPatient())) {
			errors.add(new Error("patient", ValidationMessages.INVALID_PATIENT));
		}
		
		return errors;
	}
}
