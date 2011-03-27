package br.com.easyclinica.domain.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import br.com.easyclinica.domain.types.Address;

@Entity
public class Patient {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Embedded
	private Address address;
	@ManyToOne(fetch = FetchType.EAGER)
	private HealthCarePlan healthCarePlan;
	@Type(type = "text")
	private String observations;
	private String cellphone;
	private String telephone;
	private String commercialPhone;
	private Calendar birthDate;
	private String rg;
	private String cpf;
	private String profession;
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;
	private String email;
	private String healthCarePlanCode;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	private List<Appointment> appointments;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	private List<Anamnese> anamneses;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	private List<Receipt> receipts;

	public Patient() {
	}

	public Patient(int id) {
		this.id = id;
		appointments = new ArrayList<Appointment>();
		anamneses = new ArrayList<Anamnese>();
	}

	public static Patient empty() {
		Patient empty = new Patient();
		return empty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public HealthCarePlan getHealthCarePlan() {
		return healthCarePlan;
	}

	public void setHealthCarePlan(HealthCarePlan healthCarePlan) {
		this.healthCarePlan = healthCarePlan;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	protected void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public void setHealthCarePlanCode(String healthCarePlanCode) {
		this.healthCarePlanCode = healthCarePlanCode;
	}

	public String getHealthCarePlanCode() {
		return healthCarePlanCode;
	}

	public List<Anamnese> getAnamneses() {
		return anamneses;
	}

	protected void setAnamneses(List<Anamnese> anamneses) {
		this.anamneses = anamneses;
	}

	public void setReceipts(List<Receipt> receipts) {
		this.receipts = receipts;
	}

	public List<Receipt> getReceipts() {
		return receipts;
	}

	public void addAnamnese(Anamnese a) {
		a.setPatient(this);
		anamneses.add(a);
	}

	public String getCommercialPhone() {
		return commercialPhone;
	}

	public void setCommercialPhone(String commercialPhone) {
		this.commercialPhone = commercialPhone;
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
}
