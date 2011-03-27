package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;

@Entity
public class Receipt {
	@Id
	@GeneratedValue
	private int id;
	private Calendar date;
	private BigDecimal amount;
	private String inNameOf;
	private String cpf;
	@Enumerated(EnumType.STRING)
	private Kinship kinship;
	@Type(type = "text")
	private String observations;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Index(name = "receiptPatientIndex")
	private Patient patient;

	public Receipt(int id) {
		this.id = id;
	}

	public Receipt() {
		this(0);
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Calendar getDate() {
		return date;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setInNameOf(String inNameOf) {
		this.inNameOf = inNameOf;
	}

	public String getInNameOf() {
		return inNameOf;
	}

	public void setKinship(Kinship kinship) {
		this.kinship = kinship;
	}

	public Kinship getKinship() {
		return kinship;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getObservations() {
		return observations;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public static Receipt empty() {
		return new Receipt();
	}
}
