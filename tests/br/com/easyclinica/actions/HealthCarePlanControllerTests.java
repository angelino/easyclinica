package br.com.easyclinica.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.repositories.AllServiceTables;
import br.com.easyclinica.domain.validators.HealthCarePlanValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.view.Messages;
import br.com.easyclinica.view.paginator.PaginatedResult;
import br.com.easyclinica.view.paginator.Paginator;

public class HealthCarePlanControllerTests {

	private AllHealthCarePlans allHealthCares;
	private MockResult result;
	private HealthCarePlanController controller;
	private MockValidator validator;
	private HealthCarePlanValidator healthCarePlanValidator;
	private ErrorTranslator translator;
	private AllServiceTables allServiceTables;
	private Paginator paginator;

	@Before
	public void setUp() {
		allHealthCares = mock(AllHealthCarePlans.class);
		allServiceTables = mock(AllServiceTables.class);
		result = spy(new MockResult());
		validator = spy(new MockValidator());
		healthCarePlanValidator = mock(HealthCarePlanValidator.class);
		translator = mock(ErrorTranslator.class);
		paginator = mock(Paginator.class);
		
		controller = new HealthCarePlanController(allHealthCares, allServiceTables, result, 
				validator, healthCarePlanValidator, translator, paginator);
	}

	@Test
	public void shouldGetAllHealthCares() {
		PaginatedResult<HealthCarePlan> paginatedResult = new PaginatedResult<HealthCarePlan>(new ArrayList<HealthCarePlan>(), 1, 10);
		when(paginator.paginate(allHealthCares, 1)).thenReturn(paginatedResult);
		
		controller.index(Paginator.firstPage());
		
		assertEquals(paginatedResult, result.included("healthcares"));
	}
	
	@Test
	public void shouldHaveEnoughInfoToAddANewHealthCarePlan() {
		controller.newForm();
		
		assertNotNull(result.included("healthCarePlan"));
		assertNotNull(result.included("tables"));
		verify(allServiceTables).get();
	}
	
	@Test
	public void shouldSaveANewHealthCarePlan() {
		HealthCarePlan plan = aHealthCarePlan();
		
		controller.save(plan);
		
		verify(allHealthCares).add(plan);
		assertEquals(Messages.HEALTH_CARE_PLAN_ADDED, result.included("message"));
	}
	
	@Test(expected=ValidationException.class)
	public void shouldValidateBeforeSave() {
		
		supposingThatValidationHasFailed();
		
		HealthCarePlan plan = aHealthCarePlan();
		
		controller.save(plan);
	}
	
	@Test
	public void shouldLoadAHealthCarePlanToBeUpdated() {
		HealthCarePlan plan = aHealthCarePlan();
		when(allHealthCares.getById(1)).thenReturn(plan);
		controller.edit(1);
		
		assertEquals(plan, result.included("healthCarePlan"));
		assertNotNull(result.included("tables"));
		verify(allServiceTables).get();
	}
	
	@Test
	public void shouldSaveAnUpdatedHealthCarePlan() {
		HealthCarePlan plan = aHealthCarePlan();
		controller.update(plan);
		
		verify(allHealthCares).update(plan);
		
		assertEquals(Messages.HEALTH_CARE_PLAN_UPDATED, result.included("message"));
	}
	
	@Test(expected=ValidationException.class)
	public void shouldValidateBeforeUpdate() {
		
		supposingThatValidationHasFailed();
		
		HealthCarePlan plan = aHealthCarePlan();
		
		controller.update(plan);
	}
	
	@Test
	public void shouldShowAHealthCarePlan() {
		HealthCarePlan plan = aHealthCarePlan();
		when(allHealthCares.getById(1)).thenReturn(plan);
		
		controller.show(1);
		
		assertEquals(plan, result.included("healthCarePlan"));
	}

	private void supposingThatValidationHasFailed() {
		validator.add(new ValidationMessage("message", "category"));
	}

	private HealthCarePlan aHealthCarePlan() {
		return new HealthCarePlanBuilder().instance();
	}
}
