package br.com.easyclinica.domain.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;

@Entity
public class Prescription {
	@Id
	@GeneratedValue
	private int id;
	private Calendar date;
	@Type(type = "text")
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	@Index(name = "prescriptionPatientIndex")
	private Patient patient;
	@ManyToOne(fetch = FetchType.EAGER)
	@Index(name = "prescriptionDoctorIndex")
	private Doctor doctor;

	
	public Prescription(int id) {
		this.id = id;
	}

	public Prescription() {
		this(0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
}
