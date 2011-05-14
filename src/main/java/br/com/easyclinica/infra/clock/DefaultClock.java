package br.com.easyclinica.infra.clock;

import java.util.Calendar;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class DefaultClock implements Clock {

	public Calendar now() {
		return Calendar.getInstance();
	}

}
