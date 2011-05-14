package br.com.easyclinica.tests.helpers;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.easyclinica.domain.entities.Kinship;
import br.com.easyclinica.domain.entities.Receipt;
import br.com.easyclinica.domain.entities.Patient;

public class ReceiptBuilder {
	private Receipt instance;

	public ReceiptBuilder() {
		this(0);
	}
	
	public ReceiptBuilder(int id) {
		this.instance = new Receipt(id);
	}

	public ReceiptBuilder toThePatient(Patient patient) {
		instance.setPatient(patient);
		return this;
	}
	
	public ReceiptBuilder inNameOf(String name) {
		instance.setInNameOf(name);
		return this;
	}
	
	public ReceiptBuilder his(Kinship kinship) {
		instance.setKinship(kinship);
		return this;
	}
	
	public ReceiptBuilder ownerOfTheCpf(String cpf) {
		instance.setCpf(cpf);
		return this;
	}
	
	public ReceiptBuilder withAmount(BigDecimal amount) {
		instance.setAmount(amount);
		return this;
	}
	
	public Receipt instance() {
		return this.instance;
	}

	public ReceiptBuilder withBirthDate(Calendar date) {
		instance.setBirthDate(date);
		return this;
	}
}
