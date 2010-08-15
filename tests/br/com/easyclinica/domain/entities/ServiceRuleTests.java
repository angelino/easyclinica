package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.tests.helpers.ServiceBuilder;

public class ServiceRuleTests {

	private Service service;
	
	@Before
	public void setUp() {
		service = new ServiceBuilder().instance();
	}
	
	@Test
	public void shouldBeValuedAsCh() {
		ServiceRule rule = new ServiceRule(service, new CH(10));
		
		assertTrue(rule.isRulingCh());
		assertFalse(rule.isRulingValue());
	}
	
	@Test
	public void shouldBeValuedAsMoney() {
		ServiceRule rule = new ServiceRule(service, new Money(10));
		
		assertFalse(rule.isRulingCh());
		assertTrue(rule.isRulingValue());
	}
}
