package br.com.easyclinica.domain.validators.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.domain.validators.ValidatorUtils;
import br.com.easyclinica.domain.validators.impl.DefaultDoctorValidator;
import br.com.easyclinica.tests.helpers.DoctorBuilder;
import br.com.easyclinica.tests.helpers.SpecialtyBuilder;

public class DoctorValidatorTests {
	private DefaultDoctorValidator validator;

	@Before
	public void setUp() {
		CPFValidator cpfValidator = new CPFValidator();
		ValidatorUtils validatorUtils = new ValidatorUtils(cpfValidator);
		validator = new DefaultDoctorValidator(validatorUtils);
	}
	
	@Test
	public void shouldNotReturnErrorsOnAValidDoctor() {
		Doctor doctor = new DoctorBuilder().withSpecialty(aSpecialty()).instance();
		
		List<Error> errors = validator.validate(doctor);		
		assertEquals(0, errors.size());
	
	}
	
	@Test
	public void shouldReturnErrorIfNameIsNotPresent() {
		Doctor doctor = new DoctorBuilder().withName("").withSpecialty(aSpecialty()).instance();
		
		List<Error> errors = validator.validate(doctor);		
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_NAME, errors.get(0).getKey());
	
	}
	
	@Test
	public void shouldReturnErrorIfCrmIsNotPresent() {
		Doctor doctor = new DoctorBuilder().withCrm("").withSpecialty(aSpecialty()).instance();
		
		List<Error> errors = validator.validate(doctor);		
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_CRM, errors.get(0).getKey());
	}
	
	@Test
	public void shouldReturnErrorIfSpecialtyIsNotPresent() {
		Doctor doctor = new DoctorBuilder().instance();
		
		List<Error> errors = validator.validate(doctor);		
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_SPECIALTY, errors.get(0).getKey());
	}
	
	public Specialty aSpecialty() {
		Specialty specialty = new SpecialtyBuilder(1).instance();
		return specialty;
	}
}
