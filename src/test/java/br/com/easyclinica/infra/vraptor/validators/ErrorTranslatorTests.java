package br.com.easyclinica.infra.vraptor.validators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import br.com.easyclinica.domain.validators.Error;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.view.Results;

public class ErrorTranslatorTests {

	@Test
	public void shouldCreateOneVRaptorErrorForEachDomainError() {
		MockValidator validator = new MockValidator();
		DefaultErrorTranslator translator = new DefaultErrorTranslator(validator);
		
		List<Error> errors = new ArrayList<Error>();
		errors.add(new Error("cat1", "key1"));
		errors.add(new Error("cat2", "key2"));
		
		try {
			translator.translate(errors);
			validator.onErrorUse(Results.http()).body("test");
			
			fail();
		}
		catch(ValidationException e) {
			List<Message> translatedErrors = e.getErrors();
			assertEquals("cat1", translatedErrors.get(0).getCategory());
			assertEquals("key1", translatedErrors.get(0).getMessage());
			assertEquals("cat2", translatedErrors.get(1).getCategory());
			assertEquals("key2", translatedErrors.get(1).getMessage());
		}
	}
}
