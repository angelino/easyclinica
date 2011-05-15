package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.infra.multitenancy.LoggedUser;
import br.com.easyclinica.infra.multitenancy.LoggedUsersByTenant;

@Resource
public class LogOffController {

	private final Result result;
	private final LoggedUsersByTenant loggedUsers;
	private final LoggedUser loggedUser;
	
	public LogOffController(LoggedUser loggedUser, LoggedUsersByTenant loggedUsers, Result result) {
		this.loggedUser = loggedUser;
		this.loggedUsers = loggedUsers;
		this.result = result;
	}
	
	@Get
	@Path("/logoff")
	public void logoff() {
		
		loggedUsers.remove(loggedUser.getEmployee().getLogin());
		loggedUser.logoff();
		
		result.redirectTo(LoginController.class).login();
	}
}
