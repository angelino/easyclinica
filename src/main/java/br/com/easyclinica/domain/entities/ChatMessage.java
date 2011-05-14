package br.com.easyclinica.domain.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

@Entity
public class ChatMessage {
	@Id @GeneratedValue
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@Index(name="chatmessage_from")
	private Employee from;
	@ManyToOne(fetch=FetchType.EAGER)
	@Index(name="chatmessage_to")
	private Employee to;
	private Calendar date;
	private String message;

	public ChatMessage() {}
	
	public ChatMessage(Employee from, Employee to, Calendar date, String message) {
		this.from = from;
		this.to = to;
		this.date = date;
		this.message = message;
	}
	
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public Employee getFrom() {
		return from;
	}
	public void setFrom(Employee from) {
		this.from = from;
	}
	public Employee getTo() {
		return to;
	}
	public void setTo(Employee to) {
		this.to = to;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
