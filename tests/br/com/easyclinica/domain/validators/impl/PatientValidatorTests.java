package br.com.easyclinica.domain.validators.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.PatientBuilder;

public class PatientValidatorTests {

	private DefaultPatientValidator validator;
	private HealthCarePlan plan;

	@Before
	public void setUp() {
		validator = new DefaultPatientValidator();
		
		plan = new HealthCarePlanBuilder().withId(123).instance();
	}
	
	@Test
	public void shouldNotReturnErrorsOnAValidPatient() {
		Patient patient = new PatientBuilder().withHealthCarePlan(plan).instance();
		
		List<Error> errors = validator.validate(patient);		
		assertEquals(0, errors.size());
	}
	
	@Test
	public void shouldReturnErrorIfNameIsNotPresent() {
		Patient patient = new PatientBuilder().withHealthCarePlan(plan).withName("").instance();
		
		List<Error> errors = validator.validate(patient);		
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_NAME, errors.get(0).getKey());	
	}

	@Test
	public void shouldReturnErrorIfTelephoneIsNotPresent() {
		Patient patient = new PatientBuilder().withHealthCarePlan(plan).withTelephone("").instance();
		
		List<Error> errors = validator.validate(patient);
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_TELEPHONE, errors.get(0).getKey());	
	}
	
	@Test
	public void shouldReturnErrorIfHealthCareIsNotPresent() {
		Patient patient = new PatientBuilder()
			.withHealthCarePlan(new HealthCarePlanBuilder().withId(0).instance()).instance();
		
		List<Error> errors = validator.validate(patient);		
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_HEALTHCAREPLAN, errors.get(0).getKey());	
	}

}
