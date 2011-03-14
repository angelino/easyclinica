package br.com.easyclinica.domain.validators.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.PatientValidator;
import br.com.easyclinica.domain.validators.ValidationMessages;

@Component
public class DefaultPatientValidator implements PatientValidator {

	private final CPFValidator cpfValidator;
	public DefaultPatientValidator(CPFValidator cpfValidator) {
		this.cpfValidator = cpfValidator;
	}
	
	public List<Error> validate(Patient patient) {
		List<Error> errors = new ArrayList<Error>();
		if(thereIsNoNome(patient)) {
			errors.add(new Error("patient", ValidationMessages.INVALID_NAME));
		}
		if(thereIsNoPhone(patient)) {
			errors.add(new Error("patient", ValidationMessages.INVALID_TELEPHONE));
		}
		if(thereIsNoHealthCarePlan(patient)) {
			errors.add(new Error("patient", ValidationMessages.INVALID_HEALTHCAREPLAN));
		}
		if(thereIsCpf(patient) && cpfIsInvalid(patient)) {
			errors.add(new Error("patient", ValidationMessages.INVALID_CPF));
		}
		return errors;
	}

	private boolean cpfIsInvalid(Patient patient) {
		try {
			cpfValidator.assertValid(patient.getCpf());
			return false;
		}
		catch(Exception e) {
			return true;
		}
	}

	private boolean thereIsCpf(Patient patient) {
		return patient.getCpf() != null && patient.getCpf().length()>0;
	}

	private boolean thereIsNoNome(Patient patient) {
		return patient.getName() == null || patient.getName().toString().length() == 0;
	}
	private boolean thereIsNoPhone(Patient patient) {
		return patient.getTelephone() == null || patient.getTelephone().toString().length() == 0;
	}
	private boolean thereIsNoHealthCarePlan(Patient patient) {
		return patient.getHealthCarePlan() == null || patient.getHealthCarePlan().getId() == 0;
	}

}
