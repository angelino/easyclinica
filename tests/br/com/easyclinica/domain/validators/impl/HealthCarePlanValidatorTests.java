package br.com.easyclinica.domain.validators.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.domain.validators.impl.DefaultHealthCarePlanValidator;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;

public class HealthCarePlanValidatorTests {

	private DefaultHealthCarePlanValidator validator;

	@Before
	public void setUp() {
		validator = new DefaultHealthCarePlanValidator();
	}
	
	@Test
	public void shouldNotReturnErrorsOnAValidHealthCarePlan() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		
		List<Error> errors = validator.validate(plan);		
		assertEquals(0, errors.size());
	
	}
	
	@Test
	public void shouldReturnErrorIfNameIsNotPresent() {
		HealthCarePlan plan = new HealthCarePlanBuilder().withName("").instance();
		
		List<Error> errors = validator.validate(plan);		
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_NAME, errors.get(0).getKey());
	
	}
	
	@Test
	public void shouldReturnErrorIfCHIsZeroOrNotPresent() {
		HealthCarePlan plan = new HealthCarePlanBuilder().withCh(0).instance();
		
		List<Error> errors = validator.validate(plan);		
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_CH, errors.get(0).getKey());
	}
}
