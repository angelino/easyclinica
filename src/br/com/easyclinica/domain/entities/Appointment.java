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
	private double appointmentAmount;
	private double procedureAmount;
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

	public double getAppointmentAmount() {
		return appointmentAmount;
	}

	public double getProcedureAmount() {
		return procedureAmount;
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

	public void markAsReturn() {
		this.isReturn = true;
	}

	public void recalculate() {
		procedureAmount = 0;
		
		for(AppointmentProcedure procedure : procedures) {
			procedureAmount += procedure.getTotalAmount();
		}
	}	
	
}
