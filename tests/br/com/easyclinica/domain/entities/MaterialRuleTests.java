package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.MaterialBuilder;

public class MaterialRuleTests {

	private HealthCarePlan plan;
	private Material material;
	private ServicesTable table;
	
	@Before
	public void setUp() {
		plan = new HealthCarePlanBuilder().instance();
		table = new ServicesTable(new Name("table"));
		material = new MaterialBuilder(table).instance();
	}
	
	@Test
	public void shouldBeValuedAsCh() {
		MaterialRule rule = new MaterialRule(plan, material, new CH(10));
		
		assertTrue(rule.isRulingCh());
		assertFalse(rule.isRulingValue());
	}
	
	@Test
	public void shouldBeValuedAsMoney() {
		MaterialRule rule = new MaterialRule(plan, material, new Money(10));
		
		assertFalse(rule.isRulingCh());
		assertTrue(rule.isRulingValue());
	}
}
