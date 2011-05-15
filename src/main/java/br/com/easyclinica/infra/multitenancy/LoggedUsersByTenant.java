package br.com.easyclinica.infra.multitenancy;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class LoggedUsersByTenant {

	private final LoggedUsers loggedUsers;
	private final Tenant tenant;

	public LoggedUsersByTenant(Tenant tenant, LoggedUsers loggedUsers) {
		this.tenant = tenant;
		this.loggedUsers = loggedUsers;
	}

	public void passedBy(String user) {
		loggedUsers.passedBy(tenant.getDomain(), user);
	}

	public Calendar firstPassedBy(String user) {
		return loggedUsers.firstPassedBy(tenant.getDomain(), user);
	}
	
	public List<String> onlineUsers() {
		return loggedUsers.getLoggedUsersIn(tenant.getDomain());
	}

	public void remove(String user) {
		loggedUsers.remove(tenant.getDomain(), user);
	}
	
}
