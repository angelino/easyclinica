package br.com.easyclinica.domain.validators.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.easyclinica.domain.entities.Anamnese;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.domain.validators.ValidatorUtils;
import br.com.easyclinica.tests.helpers.AnamneseBuilder;
import br.com.easyclinica.tests.helpers.DoctorBuilder;

public class DefaultAnamneseValidatorTests {

	private DefaultAnamneseValidator validator;
	
	@Before
	public void setUp() {
		CPFValidator cpfValidator = new CPFValidator();
		ValidatorUtils validatorUtils = new ValidatorUtils(cpfValidator);
		validator = new DefaultAnamneseValidator(validatorUtils);
	}
	
	@Test
	public void shouldNotReturnErrorsOnAValidAnamnese() {
		Anamnese anamnese = new AnamneseBuilder().withText("text").withDoctor(aDoctor()).instance();
		
		List<Error> errors = validator.validate(anamnese);		
		assertEquals(0, errors.size());
	}
	
	@Test
	public void shouldReturnErrorIfDoctorIsNotPresent() {
		Anamnese anamnese = new AnamneseBuilder().withText("text").instance();
		
		List<Error> errors = validator.validate(anamnese);		
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_DOCTOR, errors.get(0).getKey());
	}

	@Test
	public void shouldReturnErrorIfTextIsNotPresent() {
		Anamnese anamnese = new AnamneseBuilder().withDoctor(aDoctor()).instance();
		
		List<Error> errors = validator.validate(anamnese);
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_ANAMNESE, errors.get(0).getKey());
	}
	
	private Doctor aDoctor() {
		return new DoctorBuilder(123).instance();
	}
}
