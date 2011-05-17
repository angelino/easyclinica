package br.com.easyclinica.domain.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;

@Entity
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Schedule {
	@Id
	@GeneratedValue
	private int id;

	private Calendar startTime;
	private Calendar arrivalTime;
	private String subject;
	@Type(type = "text")
	private String description;
	@ManyToOne(fetch = FetchType.EAGER)
	@Index(name = "ScheduleDoctorIndex")
	private Doctor doctor;
	private boolean isTreated;

	public Schedule(int id) {
		this.id = id;
	}

	public Schedule() {
		this(0);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setArrivalTime(Calendar arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Calendar getArrivalTime() {
		return arrivalTime;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Doctor getDoctor() {
		return doctor;
	}
	
	protected void setTreated(boolean isTreated) {
		this.isTreated = isTreated;
	}

	public boolean isTreated() {
		return isTreated;
	}

	public void markAsTreated() {
		this.isTreated = true;
	}
	
	public void markAsNotTreated() {
		this.isTreated = false;
	}
	
	public static Schedule empty() {
		Schedule empty = new Schedule();
		return empty;
	}

}
