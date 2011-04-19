package br.com.easyclinica.tests.helpers;

import java.math.BigDecimal;

import br.com.easyclinica.domain.entities.Kinship;
import br.com.easyclinica.domain.entities.Receipt;
import br.com.easyclinica.domain.entities.Patient;

public class ReceiptBuilder {
	private Receipt Receipt;

	public ReceiptBuilder() {
		this(0);
	}
	
	public ReceiptBuilder(int id) {
		this.Receipt = new Receipt(id);
	}

	public ReceiptBuilder toThePatient(Patient patient) {
		Receipt.setPatient(patient);
		return this;
	}
	
	public ReceiptBuilder inNameOf(String name) {
		Receipt.setInNameOf(name);
		return this;
	}
	
	public ReceiptBuilder his(Kinship kinship) {
		Receipt.setKinship(kinship);
		return this;
	}
	
	public ReceiptBuilder ownerOfTheCpf(String cpf) {
		Receipt.setCpf(cpf);
		return this;
	}
	
	public ReceiptBuilder withAmount(BigDecimal amount) {
		Receipt.setAmount(amount);
		return this;
	}
	
	public Receipt instance() {
		return this.Receipt;
	}
}
