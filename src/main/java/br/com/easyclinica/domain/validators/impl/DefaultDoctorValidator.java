package br.com.easyclinica.domain.validators.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.validators.DoctorValidator;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.domain.validators.ValidatorUtils;

@Component
public class DefaultDoctorValidator implements DoctorValidator {

	private final ValidatorUtils validatorUtils;
	
	public DefaultDoctorValidator(ValidatorUtils validatorUtils){
		this.validatorUtils = validatorUtils;
	}
	
	public List<Error> validate(Doctor obj) {
		List<Error> errors = new ArrayList<Error>();
		
		if(validatorUtils.isNullOrEmpty(obj.getName())) {
			errors.add(new Error("doctor", ValidationMessages.INVALID_NAME));
		}
		if(validatorUtils.isNullOrEmpty(obj.getCrm())) {
			errors.add(new Error("doctor", ValidationMessages.INVALID_CRM));
		}
		if(obj.getSpecialty().getId() == 0) {
			errors.add(new Error("doctor", ValidationMessages.INVALID_SPECIALTY));
		}
		if(obj.getIntervalBetweenAppointments() < 5) {
			errors.add(new Error("doctor", ValidationMessages.INVALID_INTERVAL_BETWEEN_APPOINTMENTS));
		}		
		return errors;
	}
}
