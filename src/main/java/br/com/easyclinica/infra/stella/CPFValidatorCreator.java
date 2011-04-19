package br.com.easyclinica.infra.stella;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class CPFValidatorCreator implements ComponentFactory<CPFValidator> {

	public CPFValidator getInstance() {
		return new CPFValidator();
	}

}
