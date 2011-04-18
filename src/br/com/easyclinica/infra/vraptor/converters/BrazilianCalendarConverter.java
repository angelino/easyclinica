package br.com.easyclinica.infra.vraptor.converters;

import java.net.URLDecoder;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.converter.ConversionError;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

@Convert(Calendar.class)
@ApplicationScoped
public class BrazilianCalendarConverter implements Converter<Calendar> {

	public Calendar convert(String value, Class<? extends Calendar> type,
			ResourceBundle bundle) {
		
		try {
			value = URLDecoder.decode(value, "application/x-www-form-urlencoded");
			
			String format = "dd/MM/yyyy";
			if(value.contains(":")) format += " H:m";
			
			Date convertedDate = new SimpleDateFormat(format).parse(value);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(convertedDate);
			
			return calendar;
		} catch (Exception e) {
			throw new ConversionError(MessageFormat.format(bundle.getString("is_not_a_valid_date"), value));
		}
		
	}

}
