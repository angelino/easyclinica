package br.com.easyclinica.actions;

import java.util.Calendar;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Message;
import br.com.easyclinica.domain.repositories.AllMessages;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Resource
public class MessagesController {

	private final AllMessages messages;
	private final Result result;
	private final LoggedUser loggedUser;
	
	public MessagesController(Result result, AllMessages messages, LoggedUser loggedUser) {
		this.result = result;
		this.messages = messages;
		this.loggedUser = loggedUser;
	}
	
	@Post
	@Path("/mensagens/_nova")
	public void add(Message message) {
		message.setDate(Calendar.getInstance());
		message.setEmployee(loggedUser.getEmployee());
		
		messages.add(message);
		
		result.redirectTo(MessagesController.class).recents();
	}
	
	@Get
	@Path("/mensagens/_recentes")
	public void recents() {
		result.include("messages", messages.recents());
	}
}
