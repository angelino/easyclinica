package br.com.easyclinica.infra.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Anamnese;
import br.com.easyclinica.domain.repositories.AllAnamneses;

@Component
public class AnamneseDao implements AllAnamneses {

	private final Session session;

	public AnamneseDao(Session session) {
		this.session = session;
	}

	public void add(Anamnese anamnese) {
		session.save(anamnese);
	}

	public Anamnese getById(int id) {
		return (Anamnese) session.load(Anamnese.class, id);
	}

	public void update(Anamnese anamnese) {
		session.merge(anamnese);
	}

	public void delete(Anamnese anamnese) {
		session.delete(anamnese);
	}

}
