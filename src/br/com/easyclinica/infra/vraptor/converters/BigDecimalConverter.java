package br.com.easyclinica.infra.vraptor.converters;

import java.math.BigDecimal;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;

@Convert(BigDecimal.class)
public class BigDecimalConverter implements Converter<BigDecimal> {

	public BigDecimal convert(String value, Class<? extends BigDecimal> type,
			ResourceBundle resource) {
		
		value = value.replace(',', '.');		
		if(value == null || value.equals("")) {  
            value = "0.00";  
        }  
         
		return new BigDecimal(value);
	}

}
