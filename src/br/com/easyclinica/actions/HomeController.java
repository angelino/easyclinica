package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.repositories.AllMessages;
import br.com.easyclinica.infra.gravatar.GravatarImage;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Resource
public class HomeController {

	private final AllMessages messages;
	private final Result result;
	private final LoggedUser loggedUser;

	public HomeController(AllMessages messages, Result result, LoggedUser loggedUser) {
		this.messages = messages;
		this.result = result;
		this.loggedUser = loggedUser;
	}
	
	@Get
	@Path("/")
	public void dashboard(){
		result.include("gravatar", new GravatarImage());
		result.include("messages", messages.recents());
		result.include("loggedUser", loggedUser);
	}

	@Get
	@Path("/nao-autorizado")
	public void notAuthorized() {
	}
}
