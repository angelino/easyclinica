package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

@Entity
public class PrecifiedAssistant {

	@Id
	@GeneratedValue
	private int id;

	private String name;
	private int ch;
	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	private AssistantType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@Index(name = "PAProcedureIndex")
	private AppointmentProcedure procedure;

	public PrecifiedAssistant() {
	}

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

	public void setType(AssistantType type) {
		this.type = type;
	}

	public AssistantType getType() {
		return type;
	}

	public static PrecifiedAssistant empty() {
		return new PrecifiedAssistant(0);
	}
}
