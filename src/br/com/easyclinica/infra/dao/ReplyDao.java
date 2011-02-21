package br.com.easyclinica.infra.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Reply;
import br.com.easyclinica.domain.repositories.AllReplies;

@Component
public class ReplyDao implements AllReplies{

	private final Session session;
	public ReplyDao(Session session) {
		this.session = session;
	}
	public void add(Reply reply) {
		session.save(reply);
	}

}
