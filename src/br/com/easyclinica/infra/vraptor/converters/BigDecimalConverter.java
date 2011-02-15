package br.com.easyclinica.infra.vraptor.converters;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;

@Convert(BigDecimal.class)
public class BigDecimalConverter implements Converter<BigDecimal> {

	public BigDecimal convert(String value, Class<? extends BigDecimal> type,
			ResourceBundle resource) {
		
		value = value.replace(',', '.');		
		if(value == null || value.equals("")) {  
            return BigDecimal.ZERO;  
        }  
        
		BigDecimal converted = new BigDecimal(value);  
		converted.setScale(2, RoundingMode.CEILING);
		
		return converted;
	}

}
