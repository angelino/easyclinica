package br.com.easyclinica.actions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.easyclinica.domain.entities.HealthCare;
import br.com.easyclinica.domain.repositories.AllHealthCares;

public class HealthCareControllerTests {

	private AllHealthCares allHealthCares;
	private MockResult result;
	private HealthCareController controller;

	@Before
	public void SetUp() {
		allHealthCares = mock(AllHealthCares.class);
		result = new MockResult();
		
		controller = new HealthCareController(allHealthCares, result);
	}
	
	@Test
	public void shouldGetAllHealthCares() {
		List<HealthCare> list = new ArrayList<HealthCare>();
		when(allHealthCares.get()).thenReturn(list);
		
		controller.index();
		
		assertEquals(list, result.included("healthcares"));
	}
}
