package br.com.easyclinica.actions;

import java.util.Calendar;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Message;
import br.com.easyclinica.domain.repositories.AllMessages;

@Resource
public class MessagesController {

	private final AllMessages messages;
	private final Result result;
	public MessagesController(Result result, AllMessages messages) {
		this.result = result;
		this.messages = messages;
	}
	
	@Post
	@Path("/mensagens")
	public void add(Message message) {
		message.setDate(Calendar.getInstance());
		messages.add(message);
		
		result.redirectTo(HomeController.class).dashboard();
	}
}
