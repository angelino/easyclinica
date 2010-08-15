package br.com.easyclinica.actions;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.validators.DoctorValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.paginator.PaginatedResult;
import br.com.easyclinica.view.paginator.Paginator;

public class DoctorControllerTests {
	private AllDoctors allDoctors;
	private MockResult result;
	private DoctorController controller;
	private MockValidator validator;
	private DoctorValidator doctorValidator;
	private ErrorTranslator translator;
	private Paginator paginator;
	
	@Before
	public void setUp() {
		allDoctors = mock(AllDoctors.class);
		result = spy(new MockResult());
		validator = spy(new MockValidator());
		doctorValidator = mock(DoctorValidator.class);
		translator = mock(ErrorTranslator.class);
		paginator = mock(Paginator.class);
		
		controller = new DoctorController(allDoctors, result,validator,doctorValidator,translator,paginator);
	}

	@Test
	public void shouldGetAllDoctors() {
		PaginatedResult<Doctor> paginatedResult = new PaginatedResult<Doctor>(new ArrayList<Doctor>(), 1, 10);
		when(paginator.paginate(allDoctors, 1)).thenReturn(paginatedResult);
		
		controller.index(Paginator.firstPage());
		
		assertEquals(paginatedResult, result.included("doctors"));
	}
}
