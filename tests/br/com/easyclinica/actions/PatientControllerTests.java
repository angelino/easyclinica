package br.com.easyclinica.actions;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.view.paginator.PaginatedResult;
import br.com.easyclinica.view.paginator.Paginator;

public class PatientControllerTests {

	private AllPatients allPatients;
	private MockResult result;
	private PatientController controller;
	private Paginator paginator;

	@Before
	public void setUp() {
		allPatients = mock(AllPatients.class);
		result = new MockResult();
		paginator = mock(Paginator.class);
		
		controller = new PatientController(allPatients, result, paginator);
	}
	
	@Test
	public void shouldShowAllPatients() {
		PaginatedResult<Patient> paginatedResult = new PaginatedResult<Patient>(new ArrayList<Patient>(), 1, 10);
		when(paginator.paginate(allPatients, 1)).thenReturn(paginatedResult);
		
		controller.index(Paginator.firstPage());
		
		assertEquals(paginatedResult, result.included("patients"));
	}
}
