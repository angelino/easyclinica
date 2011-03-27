package br.com.easyclinica.domain.validators.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.PatientValidator;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.domain.validators.ValidatorUtils;

@Component
public class DefaultPatientValidator implements PatientValidator {

	private final ValidatorUtils validatorUtils;
	
	public DefaultPatientValidator(ValidatorUtils validatorUtils){
		this.validatorUtils = validatorUtils;
	}
	
	public List<Error> validate(Patient patient) {
		List<Error> errors = new ArrayList<Error>();
		if(validatorUtils.isNullOrEmpty(patient.getName())) {
			errors.add(new Error("patient", ValidationMessages.INVALID_NAME));
		}
		if(validatorUtils.isNullOrEmpty(patient.getTelephone())) {
			errors.add(new Error("patient", ValidationMessages.INVALID_TELEPHONE));
		}
		
		if(patient.getHealthCarePlan() == null || patient.getHealthCarePlan().getId() == 0) {
			errors.add(new Error("patient", ValidationMessages.INVALID_HEALTHCAREPLAN));
		}
		
		if(validatorUtils.cpfIsValid(patient.getCpf())) {
			errors.add(new Error("patient", ValidationMessages.INVALID_CPF));
		}
		return errors;
	}
}
