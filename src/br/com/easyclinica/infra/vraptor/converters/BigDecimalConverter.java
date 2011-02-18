package br.com.easyclinica.infra.vraptor.converters;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

@Convert(BigDecimal.class)
@ApplicationScoped
public class BigDecimalConverter implements Converter<BigDecimal> {

	Logger log = Logger.getLogger(BigDecimalConverter.class);
	
	public BigDecimal convert(String value, Class<? extends BigDecimal> type,
			ResourceBundle resource) {

		if(value == null || value.equals("")) {  
            return BigDecimal.ZERO;  
        }  		
        
		
		value = value.replace(',', '.');
		BigDecimal converted = new BigDecimal(value);
		converted = converted.setScale(2, RoundingMode.CEILING);
		
		//log.info("converter " + value + "=" + converted);
		
		return converted;
	}

}
