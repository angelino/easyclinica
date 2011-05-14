package br.com.easyclinica.infra.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.ChatMessage;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.repositories.AllChatMessages;

@Component
public class ChatMessageDao implements AllChatMessages {

	private final Session session;

	public ChatMessageDao(Session session) {
		this.session = session;
	}

	public void save(ChatMessage message) {
		session.save(message);
	}

	@SuppressWarnings("unchecked")
	public List<ChatMessage> allSince(Employee employee, Calendar date) {
		return session.createCriteria(ChatMessage.class)
				.add(
						Restrictions.disjunction()
						.add(Restrictions.eq("from", employee))
						.add(Restrictions.eq("to", employee))
					)
				.add(Restrictions.gt("date", date))
				.addOrder(Order.asc("date"))
				.list();
	}

}
