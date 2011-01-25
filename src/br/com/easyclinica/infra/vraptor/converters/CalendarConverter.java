package br.com.easyclinica.infra.vraptor.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.converter.ConversionError;

@Convert(Calendar.class)
public class CalendarConverter implements Converter<Calendar>{

	public Calendar convert(String arg0, Class<? extends Calendar> arg1,
			ResourceBundle bundle) {
		
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(arg0));
			return c;
		} catch (ParseException e) {
			throw new ConversionError(bundle.getString("errors.generic_invalid_date"));
		}
	}

}
