package br.com.easyclinica.infra.vraptor.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

@Convert(Calendar.class)
@ApplicationScoped
public class CalendarConverter implements Converter<Calendar> {

	public Calendar convert(String value, Class<? extends Calendar> type, ResourceBundle resource) {
		if(value == null || value.equals("")) {  
            return null;  
        } 
		
		String format = "dd/MM/yyyy";
		if(value.contains(":")) format += " H:m";
		
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat(format);
		Date convertedDate;
		try {
			convertedDate = dateTimeFormat.parse(value);
			Calendar converted = Calendar.getInstance();
			converted.setTime(convertedDate);
			
			return converted;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
