package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Message;
import br.com.easyclinica.domain.repositories.AllMessages;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Resource
public class RepliesController {

	private final Result result;
	private final AllMessages messages;
	private final LoggedUser loggedUser;
	public RepliesController(Result result, AllMessages messages, LoggedUser loggedUser){
		this.result = result;
		this.messages = messages;
		this.loggedUser = loggedUser;
	}
	
	@Post
	@Path("mensagens/{mensagem}/respostas/_nova")
	public void add(int mensagem, String reply) {
		Message msg = messages.getById(mensagem);
		msg.newReply(loggedUser.getEmployee(), reply);
		
		result.redirectTo(MessagesController.class).recents();
	}
}
