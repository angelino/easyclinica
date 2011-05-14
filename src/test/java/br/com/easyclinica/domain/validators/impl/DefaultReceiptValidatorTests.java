package br.com.easyclinica.domain.validators.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Kinship;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.Receipt;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.ValidationMessages;
import br.com.easyclinica.domain.validators.ValidatorUtils;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.PatientBuilder;
import br.com.easyclinica.tests.helpers.ReceiptBuilder;

public class DefaultReceiptValidatorTests {
private DefaultReceiptValidator validator;
	
	@Before
	public void setUp() {
		CPFValidator cpfValidator = new CPFValidator();
		ValidatorUtils validatorUtils = new ValidatorUtils(cpfValidator);
		validator = new DefaultReceiptValidator(validatorUtils);
	}
	
	@Test
	public void shouldNotReturnErrorsOnAValidReceipt() {
		Receipt receipt = new ReceiptBuilder().toThePatient(aPatient())
											  .inNameOf("Fulano")
											  .withBirthDate(Calendar.getInstance())
											  .his(Kinship.ME)
											  .ownerOfTheCpf("346.492.088-74")
											  .withAmount(new BigDecimal(20.00))
											  .instance();
		
		List<Error> errors = validator.validate(receipt);		
		assertEquals(0, errors.size());
	}
	
	@Test
	public void shouldReturnErrorIfInNameOfIsNotPresent() {
		Receipt receipt = new ReceiptBuilder().toThePatient(aPatient())
											  .his(Kinship.ME)
											  .withBirthDate(Calendar.getInstance())
											  .ownerOfTheCpf("346.492.088-74")
											  .withAmount(new BigDecimal(20.00))
											  .instance();
		
		List<Error> errors = validator.validate(receipt);
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_IN_NAME_OF, errors.get(0).getKey());
	}

	@Test
	public void shouldReturnErrorIfDateOfBirthIsNotPresent() {
		Receipt receipt = new ReceiptBuilder().toThePatient(aPatient())
											  .inNameOf("Fulano")
											  .his(Kinship.ME)
											  .ownerOfTheCpf("346.492.088-74")
											  .withAmount(new BigDecimal(20.00))
											  .instance();
		
		List<Error> errors = validator.validate(receipt);
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_BIRTHDATE, errors.get(0).getKey());
	}
	
	@Test
	public void shouldReturnErrorIfAmountIsZero() {
		Receipt receipt = new ReceiptBuilder().toThePatient(aPatient())
											  .inNameOf("Fulano")
											  .withBirthDate(Calendar.getInstance())
											  .his(Kinship.ME)
											  .ownerOfTheCpf("346.492.088-74")
											  .withAmount(new BigDecimal(0))
											  .instance();
		
		List<Error> errors = validator.validate(receipt);
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_AMOUNT, errors.get(0).getKey());
	}
	
	@Test
	public void shouldReturnErrorIfCpfIsInvalid() {
		Receipt receipt = new ReceiptBuilder().toThePatient(aPatient())
											  .inNameOf("Fulano")
											  .withBirthDate(Calendar.getInstance())
											  .his(Kinship.ME)
											  .ownerOfTheCpf("0000")
											  .withAmount(new BigDecimal(20.00))
											  .instance();
		
		List<Error> errors = validator.validate(receipt);
		assertEquals(1, errors.size());
		assertEquals(ValidationMessages.INVALID_CPF, errors.get(0).getKey());
	}
	
	private Patient aPatient() {
		return new PatientBuilder(123).withHealthCarePlan(aHealthCarePlan()).instance();
	}
	
	private HealthCarePlan aHealthCarePlan() {
		return new HealthCarePlanBuilder().withName("Plan").instance();
	}
}
