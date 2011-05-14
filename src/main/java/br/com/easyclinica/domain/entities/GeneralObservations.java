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
public class GeneralObservations {
	@Id
	@GeneratedValue
	private int id;
	private Calendar date;
	@Type(type = "text")
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	@Index(name = "generalObservationsPatientIndex")
	private Patient patient;

	public GeneralObservations(int id) {
		this.id = id;
	}

	public GeneralObservations() {
		this(0);
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
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

	public static GeneralObservations empty() {
		return new GeneralObservations();
	}
}
