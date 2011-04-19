package br.com.easyclinica.infra.multitenancy;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class Tenant {

	private final TenantUrlParser parser;
	private final LoggedUser loggedUser;

	public Tenant(TenantUrlParser parser, LoggedUser loggedUser) {
		this.parser = parser;
		this.loggedUser = loggedUser;
	}
	
	public String getDomain() {
		if(!loggedUser.isLogged()) return parser.parse();
		return loggedUser.getClinic().getDomain();
	}

}
