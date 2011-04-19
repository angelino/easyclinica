package br.com.easyclinica.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class PropertiesConfig implements Config {

	private Properties props;

	public PropertiesConfig() throws IOException {
		String name = "/easyclinica.properties";
		InputStream stream = PropertiesConfig.class.getResourceAsStream(name);
		this.props = new Properties();
		props.load(stream);
	}
	
	public String get(String key) {
		return props.getProperty(key);
	}

}
