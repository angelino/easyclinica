package br.com.easyclinica.domain.validators.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Prescription;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.PrescriptionsValidator;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.domain.validators.ValidatorUtils;

@Component
public class DefaultPrescriptionValidator  implements PrescriptionsValidator {
	private final ValidatorUtils validatorUtils;
	
	public DefaultPrescriptionValidator(ValidatorUtils validatorUtils){
		this.validatorUtils = validatorUtils;
	}
	
	public List<Error> validate(Prescription obj) {
		List<Error> errors = new ArrayList<Error>();
		
		if(validatorUtils.isNullOrEmpty(obj.getText())) {
			errors.add(new Error("text", ValidationMessages.INVALID_PRESCRIPTION));
		}
		
		if(!validatorUtils.exists(obj.getPatient())) {
			errors.add(new Error("patient", ValidationMessages.INVALID_PATIENT));
		}

		if(!validatorUtils.exists(obj.getDoctor())) {
			errors.add(new Error("doctor", ValidationMessages.INVALID_DOCTOR));
		}
		
		return errors;
	}
}
