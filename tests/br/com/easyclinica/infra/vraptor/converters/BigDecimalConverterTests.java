package br.com.easyclinica.infra.vraptor.converters;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalConverterTests {

	@Test
	public void shouldConvertNumberWithComma() {
		BigDecimal convertedNumber = convert("123,45");
		assertEquals(123.45, convertedNumber.doubleValue(), 0.00000001);
		
		BigDecimal convertedNumber2 = convert("123,00");
		assertEquals(123.0, convertedNumber2.doubleValue(), 0.00000001);

	}
	
	@Test
	public void shouldConvertNumberWithDot() {
		BigDecimal convertedNumber = convert("123.45");
		assertEquals(123.45, convertedNumber.doubleValue(), 0.00000001);

		BigDecimal convertedNumber2 = convert("123.00");
		assertEquals(123.0, convertedNumber2.doubleValue(), 0.00000001);
	}
	
	@Test
	public void shouldConvertOnlyNumber() {
		BigDecimal convertedNumber = convert("100");
		assertEquals(100.0, convertedNumber.doubleValue(), 0.00000001);
	}
	
	private BigDecimal convert(String number) {
		return new BigDecimalConverter().convert(number, BigDecimal.class, null);
	}
}
