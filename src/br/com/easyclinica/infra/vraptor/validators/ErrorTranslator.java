package br.com.easyclinica.infra.vraptor.validators;

import java.util.List;
import br.com.easyclinica.domain.validators.Error;

public interface ErrorTranslator {
	void translate(List<Error> errors);
}
