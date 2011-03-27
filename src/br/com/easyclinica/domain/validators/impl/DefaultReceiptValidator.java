package br.com.easyclinica.domain.validators.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Receipt;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ReceiptValidator;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.domain.validators.ValidatorUtils;

@Component
public class DefaultReceiptValidator implements ReceiptValidator {

	private final ValidatorUtils validatorUtils;
	
	public DefaultReceiptValidator(ValidatorUtils validatorUtils){
		this.validatorUtils = validatorUtils;
	}
	
	public List<Error> validate(Receipt obj) {
		List<Error> errors = new ArrayList<Error>();
		
		if(validatorUtils.isNullOrEmpty(obj.getInNameOf())) {
			errors.add(new Error("receipt", ValidationMessages.INVALID_IN_NAME_OF));
		}
		
		if(!validatorUtils.isMoreThanZero(obj.getAmount())) {
			errors.add(new Error("receipt", ValidationMessages.INVALID_AMOUNT));
		}
		
		if(!validatorUtils.cpfIsValid(obj.getCpf())) {
			errors.add(new Error("receipt", ValidationMessages.INVALID_CPF));
		}
		
		return errors;
	}

}
