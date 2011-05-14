package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;

@Entity
public class Appointment {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@Index(name = "appointmentPlanIndex")
	private HealthCarePlan healthCarePlan;
	@ManyToOne(fetch = FetchType.EAGER)
	@Index(name = "appointmentDoctorIndex")
	private Doctor doctor;
	@ManyToOne(fetch = FetchType.EAGER)
	@Index(name = "appointmentPatientIndex")
	private Patient patient;
	@ManyToOne(fetch = FetchType.EAGER)
	private Specialty specialty;
	private boolean isReturn;
	@Temporal(TemporalType.DATE)
	@Index(name = "appointmentDateIndex")
	private Calendar appointmentDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;

	private BigDecimal appointmentAmount;
	private BigDecimal procedureAmount;
	private BigDecimal roomRateAmount;
	
	private BigDecimal materialAmount;
	private BigDecimal medicineAmount;
	private BigDecimal assistantAmount;
	private BigDecimal totalAmount;

	@Type(type = "text")
	private String observations;

	@Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "appointment")
	private List<AppointmentProcedure> procedures;

	@ManyToOne(fetch = FetchType.EAGER)
	private Employee employee;

	public Appointment() {
		procedures = new ArrayList<AppointmentProcedure>();
		date = Calendar.getInstance();
	}

	public int getId() {
		return id;
	}

	public HealthCarePlan getHealthCarePlan() {
		return healthCarePlan;
	}

	public void setHealthCarePlan(HealthCarePlan healthCarePlan) {
		this.healthCarePlan = healthCarePlan;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public boolean isReturn() {
		return isReturn;
	}

	protected void setReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}

	public Calendar getAppointmentDate() {
		return (Calendar) appointmentDate.clone();
	}

	public void setAppointmentDate(Calendar appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public List<AppointmentProcedure> getProcedures() {
		return procedures;
	}

	public void addProcedure(AppointmentProcedure procedure) {
		procedures.add(procedure);
		recalculate();
	}

	protected void setProcedures(List<AppointmentProcedure> procedures) {
		this.procedures = procedures;
		recalculate();
	}

	protected void setId(int id) {
		this.id = id;
	}

	public void setAppointmentAmount(BigDecimal appointmentAmount) {
		this.appointmentAmount = appointmentAmount;
	}

	public BigDecimal getAppointmentAmount() {
		return appointmentAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public BigDecimal getProcedureAmount() {
		return procedureAmount;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setRoomRateAmount(BigDecimal roomRateAmount) {
		this.roomRateAmount = roomRateAmount;
	}

	public BigDecimal getRoomRateAmount() {
		return roomRateAmount;
	}

	public void setMaterialAmount(BigDecimal materialAmount) {
		this.materialAmount = materialAmount;
	}

	public BigDecimal getMaterialAmount() {
		return materialAmount;
	}

	public void setMedicineAmount(BigDecimal medicineAmount) {
		this.medicineAmount = medicineAmount;
	}

	public BigDecimal getMedicineAmount() {
		return medicineAmount;
	}

	public void setAssistantAmount(BigDecimal assistantAmount) {
		this.assistantAmount = assistantAmount;
	}

	public BigDecimal getAssistantAmount() {
		return assistantAmount;
	}

	public void markAsReturn() {
		this.isReturn = true;
	}

	public void recalculate() {
		totalAmount = BigDecimal.ZERO;
		procedureAmount = BigDecimal.ZERO;
		materialAmount = BigDecimal.ZERO;
		medicineAmount = BigDecimal.ZERO;
		assistantAmount = BigDecimal.ZERO;
		
		for (AppointmentProcedure procedure : procedures) {
			totalAmount = totalAmount.add(procedure.getTotalAmount());
			procedureAmount = procedureAmount.add(procedure.getAmount());
			
			materialAmount = materialAmount.add(procedure.getMaterialsTotal());
			medicineAmount = medicineAmount.add(procedure.getMedicinesTotal());
			assistantAmount = assistantAmount.add(procedure.getAssistantsTotal());
		}
		
		totalAmount = totalAmount.add(this.roomRateAmount);
		totalAmount = totalAmount.add(this.appointmentAmount);
	}

}
