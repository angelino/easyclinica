package br.com.easyclinica.domain.validators.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.validators.DoctorValidator;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ValidationMessages;

@Component
public class DefaultDoctorValidator implements DoctorValidator {

	public List<Error> validate(Doctor obj) {
		List<Error> errors = new ArrayList<Error>();
		
		if(!obj.getName().isValid()) {
			errors.add(new Error("doctor", ValidationMessages.INVALID_NAME));
		}
		if(!obj.getCrm().isValid()) {
			errors.add(new Error("doctor", ValidationMessages.INVALID_CRM));
		}
		
		return errors;
	}
}