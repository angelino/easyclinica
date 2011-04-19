package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Resource
public class LogOffController {

	private final LoggedUser loggedUser;
	private final Result result;
	
	public LogOffController(LoggedUser loggedUser, Result result) {
		this.loggedUser = loggedUser;
		this.result = result;
	}
	
	@Get
	@Path("/logoff")
	public void logoff() {
		loggedUser.logoff();
		result.redirectTo(LoginController.class).login();
	}
}
