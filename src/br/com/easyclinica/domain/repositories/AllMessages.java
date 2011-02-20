package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.Message;

public interface AllMessages {
	List<Message> recents();
	Message getById(int id);
	void update(Message msg);
	void add(Message message);
}
