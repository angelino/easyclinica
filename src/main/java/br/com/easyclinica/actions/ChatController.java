package br.com.easyclinica.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.easyclinica.domain.entities.ChatMessage;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.repositories.AllChatMessages;
import br.com.easyclinica.domain.repositories.AllEmployees;
import br.com.easyclinica.infra.chat.LastChatUpdate;
import br.com.easyclinica.infra.multitenancy.LoggedUser;
import br.com.easyclinica.infra.multitenancy.LoggedUsersByTenant;

@Resource
public class ChatController {

	private final LoggedUser loggedUser;
	private final AllEmployees employees;
	private final AllChatMessages chatMessages;
	private final Result result;
	private final LastChatUpdate updates;
	private final LoggedUsersByTenant loggedUsers;

	public ChatController(Result result, LoggedUser loggedUser,
			AllEmployees employees, AllChatMessages chatMessages,
			LastChatUpdate lastChatUpdate,
			LoggedUsersByTenant loggedUsers) {
		this.result = result;
		this.loggedUser = loggedUser;
		this.employees = employees;
		this.chatMessages = chatMessages;
		this.updates = lastChatUpdate;
		this.loggedUsers = loggedUsers;
	}

	@Post
	@Path("/chat")
	public void newMessage(String destinationLogin, String message) {
		Employee from = employees.getById(loggedUser.getEmployee().getId());
		ChatMessage chatMsg = from.createMessage(
				employees.getByLogin(destinationLogin), message);

		chatMessages.save(chatMsg);

		result.use(Results.status()).ok();
	}

	@Get
	@Path("/chat/online")
	public void online() {
		List<String> onlineUsers = loggedUsers.onlineUsers();
		result.use(Results.json()).from(onlineUsers).serialize();
	}
	
	@Get
	@Path("/chat/ultimas")
	public void getLatest() {
		Calendar lastUpdate = updates.getLastUpdate() == null ? loggedUsers
				.firstPassedBy(loggedUser.getEmployee().getLogin()) : updates
				.getLastUpdate();

		List<ChatMessage> messages = chatMessages.allSince(
				loggedUser.getEmployee(), lastUpdate);
		if (!messages.isEmpty()) {
			updates.setLastUpdate(messages.get(messages.size() - 1).getDate());
		}

		MsgData data = new MsgData(loggedUser.getEmployee().getLogin(), messages);
		result.use(Results.json()).withoutRoot().from(data).include("msgs")
				.serialize();
	}

	static class ChatMessageData {
		private String from;
		private String to;
		private String message;

		public ChatMessageData(ChatMessage message) {
			this.from = message.getFrom().getLogin();
			this.to = message.getTo().getLogin();
			this.message = message.getMessage();
		}

		public String getFrom() {
			return from;
		}

		public String getTo() {
			return to;
		}

		public String getMessage() {
			return message;
		}

	}

	static class MsgData {
		private String user;
		private List<ChatMessageData> msgs;

		public MsgData(String user, List<ChatMessage> messages) {
			super();
			this.user = user;
			this.msgs = new ArrayList<ChatMessageData>();
			for (ChatMessage msg : messages) {
				this.msgs.add(new ChatMessageData(msg));
			}
		}

		public String getUser() {
			return user;
		}

		public List<ChatMessageData> getMsgs() {
			return msgs;
		}

	}
}
