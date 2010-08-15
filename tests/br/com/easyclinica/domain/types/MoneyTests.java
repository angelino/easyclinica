package br.com.easyclinica.domain.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MoneyTests {
	@Test
	public void shouldStoreAMoney() {
		Money money = new Money(12.34);
		
		assertEquals(12.34, money.getMoney(), 0.001);
	}
	
	@Test
	public void shouldCompare() {
		Money one = new Money(12.34);
		Money two = new Money(12.34);
		Money three = new Money(12.35);
		
		assertTrue(one.equals(two));
		assertFalse(one.equals(three));
	}
}
