package br.com.easyclinica.actions;

import static org.mockito.Mockito.mock;

import org.junit.Before;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;

public class HealthCarePlanRuleControllerTest {

	private AllHealthCarePlans allHealthCarePlans;
	private MockResult result;
	private HealthCarePlanRuleController controller;

	@Before
	public void setUp() {
		result = new MockResult();
		allHealthCarePlans = mock(AllHealthCarePlans.class);
		controller = new HealthCarePlanRuleController(allHealthCarePlans, result);
	}
}
