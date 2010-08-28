package br.com.easyclinica.actions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.junit.Before;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.easyclinica.domain.repositories.AllSchedule;
import br.com.easyclinica.domain.validators.ScheduleValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;

public class ScheduleControllerTests {
	private AllSchedule allSchedule;
	private MockResult result;
	private ScheduleController controller;
	private MockValidator validator;
	private ScheduleValidator scheduleValidator;
	private ErrorTranslator translator;
	
	@Before
	public void setUp() {
		allSchedule = mock(AllSchedule.class);
		result = spy(new MockResult());
		validator = spy(new MockValidator());
		scheduleValidator = mock(ScheduleValidator.class);
		translator = mock(ErrorTranslator.class);
		
		controller = new ScheduleController(result, validator, allSchedule, scheduleValidator, translator);
	}
	
}
