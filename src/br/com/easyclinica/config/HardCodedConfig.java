package br.com.easyclinica.config;

import java.util.Hashtable;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class HardCodedConfig implements Config {
	
	private static Hashtable<String, String> configs;
	
	static {
		configs = new Hashtable<String, String>();
		configs.put(ConfigKeys.ELEMENTS_PER_PAGE, "10");
	}
	
	public String get(String key) {
		return configs.get(key);
	}
}
