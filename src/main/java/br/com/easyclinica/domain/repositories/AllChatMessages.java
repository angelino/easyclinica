package br.com.easyclinica.domain.repositories;

import java.util.Calendar;
import java.util.List;

import br.com.easyclinica.domain.entities.ChatMessage;
import br.com.easyclinica.domain.entities.Employee;

public interface AllChatMessages {

	void save(ChatMessage message);

	List<ChatMessage> allSince(Employee employee, Calendar date);
}
