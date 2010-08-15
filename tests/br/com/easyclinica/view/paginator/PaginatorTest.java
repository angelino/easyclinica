package br.com.easyclinica.view.paginator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.config.Config;
import br.com.easyclinica.config.ConfigKeys;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;

public class PaginatorTest {

	private AllHealthCarePlans repository;
	private Paginator paginator;

	@Before
	public void setUp() {
		Config config = mock(Config.class);
		when(config.get(ConfigKeys.ELEMENTS_PER_PAGE)).thenReturn("10");
		
		paginator = new Paginator(config);
	}
	@Test
	public void shouldPaginate() {
		repository = mock(AllHealthCarePlans.class);
		when(repository.count()).thenReturn(95);
		
		PaginatedResult<HealthCarePlan> result = paginator.paginate(repository, 2);
		
		assertEquals(10, result.getTotalPages());
		verify(repository).get(10, 10);
		
	}
}
