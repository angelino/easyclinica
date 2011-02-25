package br.com.easyclinica.infra.vraptor.converters;

import java.math.BigDecimal;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

@Convert(BigDecimal.class)
@ApplicationScoped
public class BrazilianOrAmericanBigDecimalConverter implements Converter<BigDecimal> {

	public BigDecimal convert(String value, Class<? extends BigDecimal> type,
			ResourceBundle resource) {

		if (value == null || value.equals("")) {
			return BigDecimal.ZERO;
		}
		
		value = value.replace(',', '.');
		BigDecimal converted = new BigDecimal(value);

		return converted;
	}

}
