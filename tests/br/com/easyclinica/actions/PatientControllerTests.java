package br.com.easyclinica.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.domain.validators.PatientValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.tests.helpers.PatientBuilder;
import br.com.easyclinica.view.Messages;
import br.com.easyclinica.view.paginator.PaginatedResult;
import br.com.easyclinica.view.paginator.Paginator;

public class PatientControllerTests {

	private AllPatients allPatients;
	private MockResult result;
	private PatientController controller;
	private Paginator paginator;
	private AllHealthCarePlans allHealthCarePlans;
	private MockValidator validator;
	private ErrorTranslator translator;
	private PatientValidator patientValidator;

	@Before
	public void setUp() {
		allPatients = mock(AllPatients.class);
		allHealthCarePlans = mock(AllHealthCarePlans.class);
		result = new MockResult();
		paginator = mock(Paginator.class);
		validator = new MockValidator();
		translator = mock(ErrorTranslator.class);
		patientValidator = mock(PatientValidator.class);
		
		controller = new PatientController(allPatients, allHealthCarePlans, result, validator, patientValidator, translator, paginator);
	}
	
	@Test
	public void shouldShowAllPatients() {
		PaginatedResult<Patient> paginatedResult = new PaginatedResult<Patient>(new ArrayList<Patient>(), 1, 10);
		when(paginator.paginate(allPatients, 1)).thenReturn(paginatedResult);
		
		controller.index(Paginator.firstPage());
		
		assertEquals(paginatedResult, result.included("patients"));
	}
	
	@Test
	public void shouldHaveEnoughInfoToAddANewPatient() {
		controller.newForm();
		
		assertNotNull(result.included("healthCarePlans"));
		verify(allHealthCarePlans).get();
	}

	@Test
	public void shouldSaveANewPatient() {
		Patient patient = new PatientBuilder().instance();
		
		controller.save(patient);
		
		verify(allPatients).add(patient);
		assertEquals(Messages.PATIENT_ADDED, result.included(BaseController.SUCCESS_KEY));
	}
	
	@Test(expected=ValidationException.class)
	public void shouldValidateBeforeSave() {
		
		supposingThatValidationHasFailed();
		
		Patient patient = new PatientBuilder().instance();
		
		controller.save(patient);
	}
	
	@Test
	public void shouldLoadAPatientToBeUpdated() {
		Patient patient = new PatientBuilder().instance();
		when(allPatients.getById(1)).thenReturn(patient);
		controller.edit(1);
		
		assertEquals(patient, result.included("patient"));
		assertNotNull(result.included("healthCarePlans"));
		verify(allHealthCarePlans).get();
	}
	
	@Test
	public void shouldSaveAnUpdatedHealthCarePlan() {
		Patient patient = new PatientBuilder().instance();
		controller.update(patient);
		
		verify(allPatients).update(patient);
		
		assertEquals(Messages.PATIENT_UPDATED, result.included(BaseController.SUCCESS_KEY));
	}
	
	@Test(expected=ValidationException.class)
	public void shouldValidateBeforeUpdate() {
		
		supposingThatValidationHasFailed();
		
		Patient patient = new PatientBuilder().instance();
		
		controller.update(patient);
	}
	
	@Test
	public void shouldShowAHealthCarePlan() {
		Patient patient = new PatientBuilder().instance();
		when(allPatients.getById(1)).thenReturn(patient);
		
		controller.show(1);
		
		assertEquals(patient, result.included("patient"));
	}

	private void supposingThatValidationHasFailed() {
		validator.add(new ValidationMessage("message", "category"));
	}

}
