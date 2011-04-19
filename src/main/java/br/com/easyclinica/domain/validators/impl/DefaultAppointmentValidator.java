package br.com.easyclinica.domain.validators.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.validators.AppointmentValidator;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ValidationMessages;

@Component
public class DefaultAppointmentValidator implements AppointmentValidator {

	public List<Error> validate(Appointment obj) {
		List<Error> errors = new ArrayList<Error>();
		
		if(obj.getAppointmentDate() == null) {
			errors.add(new Error("appointment", ValidationMessages.INVALID_APPOINTMENT_DATE));
		}
		
		if(obj.getPatient().getId() == 0) {
			errors.add(new Error("appointment", ValidationMessages.INVALID_PATIENT));
		}
		
		if(obj.getHealthCarePlan().getId() == 0) {
			errors.add(new Error("appointment", ValidationMessages.INVALID_HEALTHCAREPLAN));
		}
		
		if(obj.getDoctor().getId() == 0) {
			errors.add(new Error("appointment", ValidationMessages.INVALID_DOCTOR));
		}
		
		if(obj.getSpecialty().getId() == 0) {
			errors.add(new Error("appointment", ValidationMessages.INVALID_SPECIALTY));
		}
		
		return errors;
	}

}
