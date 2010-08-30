package br.com.easyclinica.domain.validators.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ScheduleValidator;
import br.com.easyclinica.domain.validators.ValidationMessages;

@Component
public class DefaultScheduleValidator implements ScheduleValidator {

	public List<Error> validate(Schedule obj) {
		List<Error> errors = new ArrayList<Error>();
		
		if(obj.getDoctor() == null) {
			errors.add(new Error("schedule", ValidationMessages.INVALID_NAME));
		}
		
		if(obj.getPatient() == null) {
			errors.add(new Error("schedule", ValidationMessages.INVALID_PATIENT));
		}
		
		if(obj.getDay() == null) {
			errors.add(new Error("schedule", ValidationMessages.INVALID_DAY));
		}
		
		return errors;
	}

}
