package br.com.easyclinica.infra.vraptor.converters;

import java.util.ResourceBundle;

import org.joda.time.LocalTime;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

@ApplicationScoped
@Convert(LocalTime.class)
public class LocalTimeConverter implements Converter<LocalTime> {

	public LocalTime convert(String value, Class<? extends LocalTime> type,
			ResourceBundle bundle) {
		return new LocalTime(value);
	}

}
