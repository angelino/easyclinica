package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Message;
import br.com.easyclinica.domain.repositories.AllMessages;

@Component
public class MessagesDAO implements AllMessages{

	private final Session session;

	public MessagesDAO(Session session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> recents() {
		return session.createCriteria(Message.class).setMaxResults(10).addOrder(Order.desc("date")).list();
	}

	public Message getById(int id) {
		return (Message) session.load(Message.class, id);
	}

	public void update(Message msg) {
		session.merge(msg);
	}

	public void add(Message message) {
		session.save(message);
	}

}
