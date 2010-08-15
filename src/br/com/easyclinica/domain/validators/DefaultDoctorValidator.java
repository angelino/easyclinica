package br.com.easyclinica.domain.validators;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Doctor;

@Component
public class DefaultDoctorValidator implements DoctorValidator {

	public List<Error> validate(Doctor obj) {
		List<Error> errors = new ArrayList<Error>();
		
		if(obj.getName().getName() == null || obj.getName().toString().length() == 0) {
			errors.add(new Error("doctor", ValidationMessages.INVALID_NAME));
		}
		if(obj.getCrm().getCrm() == null || obj.getCrm().toString().length() == 0) {
			errors.add(new Error("doctor", ValidationMessages.INVALID_CRM));
		}
		
		return errors;
	}
}
