package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.repositories.AllMessages;

@Resource
public class HomeController {

	private final AllMessages messages;
	private final Result result;

	public HomeController(AllMessages messages, Result result) {
		this.messages = messages;
		this.result = result;
	}
	
	@Get
	@Path("/")
	public void dashboard(){
		result.include("messages", messages.recents());
	}

	@Get
	@Path("/nao-autorizado")
	public void notAuthorized() {
	}
}
