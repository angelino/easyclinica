package br.com.easyclinica.actions;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.validators.DoctorValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;

public class DoctorControllerTests {
	private AllDoctors allDoctors;
	private MockResult result;
	private DoctorController controller;
	private MockValidator validator;
	private DoctorValidator doctorValidator;
	private ErrorTranslator translator;
	
	@Before
	public void setUp() {
		allDoctors = mock(AllDoctors.class);
		result = spy(new MockResult());
		validator = spy(new MockValidator());
		doctorValidator = mock(DoctorValidator.class);
		translator = mock(ErrorTranslator.class);
		
		controller = new DoctorController(allDoctors, result,validator,doctorValidator,translator);
	}

	@Test
	public void shouldGetAllDoctors() {
		List<Doctor> list = new ArrayList<Doctor>();
		when(allDoctors.get()).thenReturn(list);
		
		controller.index();
		
		assertEquals(list, result.included("doctors"));
	}
}
