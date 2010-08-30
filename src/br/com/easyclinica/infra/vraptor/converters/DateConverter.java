package br.com.easyclinica.infra.vraptor.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.converter.ConversionError;

@Convert(Date.class)
public class DateConverter implements Converter<Date>{

	public Date convert(String arg0, Class<? extends Date> arg1,
			ResourceBundle bundle) {
		
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(arg0);
		} catch (ParseException e) {
			throw new ConversionError(bundle.getString("errors.generic_invalid_date"));
		}
	}

}
