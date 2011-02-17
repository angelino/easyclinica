package br.com.easyclinica.domain.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import br.com.easyclinica.domain.types.Money;

@Entity
public class Appointment {

	@Id @GeneratedValue
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private HealthCarePlan healthCarePlan;
	@ManyToOne(fetch=FetchType.EAGER)
	private Doctor doctor;
	@ManyToOne(fetch=FetchType.EAGER)
	private Patient patient;
	@ManyToOne(fetch=FetchType.EAGER)
	private Specialty specialty;
	private boolean isReturn;
	@Temporal(TemporalType.DATE)
	private Calendar appointmentDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;
	
	@Embedded
	@AttributeOverrides({ 
			@AttributeOverride(name="amount", column=@Column(name="appointmentAmount"))
	}) 
	private Money appointmentAmount;
	
	@Embedded 
	@AttributeOverrides({ 
		@AttributeOverride(name="amount", column=@Column(name="procedureAmount"))
	}) 
	private Money procedureAmount;
	
	@Type(type="text") 
	private String observations;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="appointment", orphanRemoval=true, fetch=FetchType.EAGER) 
	private List<AppointmentProcedure> procedures;

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
		return appointmentDate;
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

	public void setAppointmentAmount(Money appointmentAmount) {
		this.appointmentAmount = appointmentAmount;
	}

	public Money getAppointmentAmount() {
		return appointmentAmount;
	}

	public Money getProcedureAmount() {
		return procedureAmount;
	}
	
	public void markAsReturn() {
		this.isReturn = true;
	}

	public void recalculate() {
		if(procedureAmount == null) procedureAmount = Money.empty();
		
		for(AppointmentProcedure procedure : procedures) {
			procedureAmount.addValueToAmount(procedure.getTotalAmount().getAmount());
		}
	}	
	
}
