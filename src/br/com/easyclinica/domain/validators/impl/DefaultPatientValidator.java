package br.com.easyclinica.domain.validators.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.PatientValidator;
import br.com.easyclinica.domain.validators.ValidationMessages;

@Component
public class DefaultPatientValidator implements PatientValidator {

	public List<Error> validate(Patient obj) {
		List<Error> errors = new ArrayList<Error>();
		if(thereIsNoNome(obj)) {
			errors.add(new Error("patient", ValidationMessages.INVALID_NAME));
		}
		if(thereIsNoPhone(obj)) {
			errors.add(new Error("patient", ValidationMessages.INVALID_TELEPHONE));
		}
		if(thereIsNoHealthCarePlan(obj)) {
			errors.add(new Error("patient", ValidationMessages.INVALID_HEALTHCAREPLAN));
		}
		return errors;
	}

	private boolean thereIsNoNome(Patient obj) {
		return obj.getName() == null || obj.getName().toString().length() == 0;
	}
	private boolean thereIsNoPhone(Patient obj) {
		return obj.getTelephone() == null || obj.getTelephone().toString().length() == 0;
	}
	private boolean thereIsNoHealthCarePlan(Patient obj) {
		return obj.getHealthCarePlan() == null || obj.getHealthCarePlan().getId() == 0;
	}

}
