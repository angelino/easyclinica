package br.com.easyclinica.domain.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.easyclinica.domain.types.Day;
import br.com.easyclinica.domain.types.Description;
import br.com.easyclinica.domain.types.Hour;
import br.com.easyclinica.domain.types.Reason;
import br.com.easyclinica.domain.types.Status;

@Entity
public class Schedule {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER) @JoinColumn(name="doctor_fk")	
	private Doctor doctor;

	@OneToOne(fetch=FetchType.EAGER) @JoinColumn(name="patient_fk")
	private Patient patient;
	
	@Embedded private Description description;
	@Embedded private Status status;
	@Embedded private Reason reason;
	@Embedded private Day day;
	
	@Embedded @AttributeOverride(name="hour", column = @Column(name="start"))
	private Hour start;
	
	@Embedded @AttributeOverride(name="hour", column = @Column(name="end"))
	private Hour end;
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Patient getPatient() {
		return patient;
	}
	
	public void setDescription(Description description) {
		this.description = description;
	}
	public Description getDescription() {
		return description;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	public Status getStatus() {
		return status;
	}
	
	public void setReason(Reason reason) {
		this.reason = reason;
	}
	public Reason getReason() {
		return reason;
	}
	
	public void setDay(Day day) {
		this.day = day;
	}
	public Day getDay() {
		return day;
	}
	
	public void setStart(Hour start) {
		this.start = start;
	}
	public Hour getStart() {
		return start;
	}
	
	public void setEnd(Hour end) {
		this.end = end;
	}
	public Hour getEnd() {
		return end;
	}
}
