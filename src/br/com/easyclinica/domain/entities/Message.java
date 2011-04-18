package br.com.easyclinica.domain.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

@Entity
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Message {

	@Id
	@GeneratedValue
	private int id;
	@Type(type = "text")
	private String text;
	@ManyToOne(fetch = FetchType.EAGER)
	private Employee employee;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
	private List<Reply> replies;

	public Message() {
		this.replies = new ArrayList<Reply>();
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	protected void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Reply newReply(Employee employee, String text) {
		Reply reply = new Reply();

		reply.setEmployee(employee);
		reply.setText(text);
		reply.setDate(Calendar.getInstance());
		reply.setMessage(this);

		replies.add(reply);
		return reply;

	}

}
