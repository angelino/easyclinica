package br.com.easyclinica.domain.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.easyclinica.tests.helpers.EmployeeBuilder;

public class MessageTests {

	@Test
	public void shouldAddAReply() {
		Message mensagem = new Message();
		Employee employee = new EmployeeBuilder().instance();
		
		mensagem.addReply(employee, "reply");
		
		assertEquals("reply", mensagem.getReplies().get(0).getText());
		assertSame(employee, mensagem.getReplies().get(0).getEmployee());
	}
}
