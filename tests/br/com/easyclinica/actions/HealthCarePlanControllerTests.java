package br.com.easyclinica.actions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import br.com.easyclinica.domain.validators.Error;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.validators.healthCarePlan.NewHealthCarePlanValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;

public class HealthCarePlanControllerTests {

	private AllHealthCarePlans allHealthCares;
	private MockResult result;
	private HealthCarePlanController controller;
	private MockValidator validator;
	private NewHealthCarePlanValidator healthCarePlanValidator;
	private ErrorTranslator translator;

	@Before
	public void SetUp() {
		allHealthCares = mock(AllHealthCarePlans.class);
		result = spy(new MockResult());
		validator = spy(new MockValidator());
		healthCarePlanValidator = spy(new NewHealthCarePlanValidator());
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
	public void shouldSaveANewHealthCarePlan() {
		HealthCarePlan plan = new HealthCarePlan(new Name("Amil"));
		
		stub(healthCarePlanValidator.validate(plan)).toReturn(anEmptyErrorList());
		controller.save(plan);
		
		verify(allHealthCares).add(plan);
	}

	private ArrayList<Error> anEmptyErrorList() {
		return new ArrayList<Error>();
	}
}
