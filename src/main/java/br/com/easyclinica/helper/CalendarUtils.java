package br.com.easyclinica.helper;

import java.util.Calendar;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class CalendarUtils {
	
	public Calendar CloneDateAndSetTime(Calendar date, int hour, int minute, int second) {
		Calendar clone = (Calendar) date.clone();
		
		clone.set(Calendar.HOUR_OF_DAY, hour);
		clone.set(Calendar.MINUTE, minute);
		clone.set(Calendar.SECOND, second);
		
		return clone;
	}
}
