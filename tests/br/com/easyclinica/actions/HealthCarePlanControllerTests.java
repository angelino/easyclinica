package br.com.easyclinica.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.validators.EntityValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;

public class HealthCarePlanControllerTests {

	private AllHealthCarePlans allHealthCares;
	private MockResult result;
	private HealthCarePlanController controller;
	private MockValidator validator;
	private EntityValidator<HealthCarePlan> healthCarePlanValidator;
	private ErrorTranslator translator;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		allHealthCares = mock(AllHealthCarePlans.class);
		result = spy(new MockResult());
		validator = spy(new MockValidator());
		healthCarePlanValidator = mock(EntityValidator.class);
		translator = mock(ErrorTranslator.class);
		
		controller = new HealthCarePlanController(allHealthCares, result, 
				validator, healthCarePlanValidator, translator);
	}

	@Test
	public void shouldGetAllHealthCares() {
		List<HealthCarePlan> list = new ArrayList<HealthCarePlan>();
		when(allHealthCares.get()).thenReturn(list);
		
		controller.index();
		
		assertEquals(list, result.included("healthcares"));
	}
	
	@Test
	public void shouldHaveAnEmptyHealthCarePlanToAdd() {
		controller.newForm();
		
		assertNotNull(result.included("healthCarePlan"));
		
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
		return HealthCarePlan.empty();
	}
}
