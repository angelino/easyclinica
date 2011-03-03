package br.com.easyclinica.config;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class HardCodedConfig implements Config {
	
	private static Hashtable<String, Object> configs;
	
	static {
		configs = new Hashtable<String, Object>();
		configs.put(ConfigKeys.ELEMENTS_PER_PAGE, "1");
		
		Calendar start = new GregorianCalendar();
		start.set(Calendar.HOUR_OF_DAY, 6);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		configs.put(ConfigKeys.CLINIC_START_OPERATION, start);
		
		Calendar end = new GregorianCalendar();
		end.set(Calendar.HOUR_OF_DAY, 21);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		configs.put(ConfigKeys.CLINIC_END_OPERATION, end);
		
		configs.put(ConfigKeys.MEDICAL_TIME, 15);
	}
	
	public Object get(String key) {
		return configs.get(key);
	}
}
