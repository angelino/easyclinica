package br.com.easyclinica.domain.validators.healthCarePlan;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.types.Name;

public class NewHealthCarePlanValidatorTests {

	private NewHealthCarePlanValidator validator;

	@Before
	public void setUp() {
		validator = new NewHealthCarePlanValidator();
	}
	
	@Test
	public void shouldNotReturnErrorsOnAValidHealthCarePlan() {
		HealthCarePlan plan = new HealthCarePlan(new Name("amil"));
		
		List<Error> errors = validator.validate(plan);		
		assertEquals(0, errors.size());
	
	}
	
	@Test
	public void shouldReturnErrorsOnAnInvalidHealthCarePlan() {
		HealthCarePlan plan = new HealthCarePlan(new Name(""));
		
		List<Error> errors = validator.validate(plan);		
		assertEquals(1, errors.size());
	
	}
}
