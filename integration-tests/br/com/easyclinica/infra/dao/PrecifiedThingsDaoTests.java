package br.com.easyclinica.infra.dao;

import org.junit.Before;

public class PrecifiedThingsDaoTests extends BaseIntegrationTests {
	
	private PrecifiedThingsDao dao;
	
	@Before
	public void setUp() {
		dao = new PrecifiedThingsDao(session);
	}
	
}
