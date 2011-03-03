package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PrecifiedAssistant {

	@Id @GeneratedValue
	private int id;
	
	private String name;
	private int ch;
	private BigDecimal amount;
	@ManyToOne(fetch=FetchType.LAZY) 
	private AppointmentProcedure procedure;
	
	public PrecifiedAssistant() {}
	
	public PrecifiedAssistant(int id) {
		this.id = id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setCh(int ch) {
		this.ch = ch;
	}
	public int getCh() {
		return ch;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getAmount() {
		return amount;
	}

	public void setProcedure(AppointmentProcedure procedure) {
		this.procedure = procedure;
	}

	public AppointmentProcedure getProcedure() {
		return procedure;
	}
}
