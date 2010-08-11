package br.com.easyclinica.infra.vraptor.validators;

import java.util.List;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.easyclinica.domain.validators.Error;

@Component
public class DefaultErrorTranslator implements ErrorTranslator {

	private final Validator validator;

	public DefaultErrorTranslator(Validator validator) {
		this.validator = validator;
	}

	public void translate(List<Error> errors) {
		for(Error e : errors) {
			validator.add(new ValidationMessage(e.getKey(), e.getCategory()));
		}
	}
}
