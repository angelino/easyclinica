package br.com.easyclinica.infra.multitenancy;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class Tenant {

	public String getUser() {
		return "easyclinica";
	}

}
