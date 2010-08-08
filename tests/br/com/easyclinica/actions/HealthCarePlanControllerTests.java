package br.com.easyclinica.actions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;

public class HealthCarePlanControllerTests {

	private AllHealthCarePlans allHealthCares;
	private MockResult result;
	private HealthCarePlanController controller;

	@Before
	public void SetUp() {
		allHealthCares = mock(AllHealthCarePlans.class);
		result = new MockResult();
		
		controller = new HealthCarePlanController(allHealthCares, result);
	}
	
	@Test
	public void shouldGetAllHealthCares() {
		List<HealthCarePlan> list = new ArrayList<HealthCarePlan>();
		when(allHealthCares.get()).thenReturn(list);
		
		controller.index();
		
		assertEquals(list, result.included("healthcares"));
	}
}
