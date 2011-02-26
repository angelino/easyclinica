package br.com.easyclinica.domain.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.easyclinica.tests.helpers.EmployeeBuilder;


public class MessageTests {

	@Test
	public void shouldAddAReply() {
		Message mensagem = new Message();
		Employee employee = new EmployeeBuilder().instance();
		
		Reply reply = mensagem.newReply(employee, "reply");
		
		assertEquals("reply", reply.getText());
		assertSame(employee, reply.getEmployee());
	}
}
