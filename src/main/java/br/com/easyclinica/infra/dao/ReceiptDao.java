package br.com.easyclinica.infra.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Receipt;
import br.com.easyclinica.domain.repositories.AllReceipts;

@Component
public class ReceiptDao implements AllReceipts {

	private final Session session;

	public ReceiptDao(Session session) {
		this.session = session;
	}
	
	public void add(Receipt receipt) {
		session.save(receipt);
	}

	public void update(Receipt receipt) {
		session.merge(receipt);
	}

	public void delete(Receipt receipt) {
		session.delete(receipt);
	}

	public Receipt getById(int id) {
		return (Receipt) session.load(Receipt.class, id);
	}
}
