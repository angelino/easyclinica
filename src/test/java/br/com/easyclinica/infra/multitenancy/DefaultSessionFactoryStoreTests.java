package br.com.easyclinica.infra.multitenancy;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DefaultSessionFactoryStoreTests {

	private DefaultSessionFactoryStore store;
	private SessionFactory factoryForTenant;

	@Before
	public void setUp() {
		store = new DefaultSessionFactoryStore();
		factoryForTenant = mock(SessionFactory.class);
	}
	
	@Test
	public void shouldStoreASessionFactory() {
		assertFalse(store.contains("tenant"));
		
		store.add("tenant", factoryForTenant);
		
		assertTrue(store.contains("tenant"));
		assertSame(factoryForTenant, store.get("tenant"));
	}
	
}
