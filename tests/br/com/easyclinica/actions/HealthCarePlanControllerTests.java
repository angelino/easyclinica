package br.com.easyclinica.actions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import br.com.easyclinica.domain.validators.Error;
import br.com.easyclinica.domain.validators.HealthCarePlanValidator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;

public class HealthCarePlanControllerTests {

	private AllHealthCarePlans allHealthCares;
	private MockResult result;
	private HealthCarePlanController controller;
	private MockValidator validator;
	private HealthCarePlanValidator healthCarePlanValidator;
	private ErrorTranslator translator;

	@Before
	public void setUp() {
		allHealthCares = mock(AllHealthCarePlans.class);
		result = spy(new MockResult());
		validator = spy(new MockValidator());
		healthCarePlanValidator = spy(new HealthCarePlanValidator());
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
		
		stub(healthCarePlanValidator.validate(plan)).toReturn(anEmptyErrorList());
		controller.save(plan);
		
		verify(allHealthCares).add(plan);
		assertEquals(Messages.HEALTH_CARE_PLAN_ADDED, result.included("message"));
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

	private HealthCarePlan aHealthCarePlan() {
		return new HealthCarePlan(new Name("Amil"));
	}
	
	private ArrayList<Error> anEmptyErrorList() {
		return new ArrayList<Error>();
	}
}
