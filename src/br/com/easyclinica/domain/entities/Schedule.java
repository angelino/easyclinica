package br.com.easyclinica.domain.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class Schedule {
	@Id @GeneratedValue
	private int id;
	
	private Calendar startTime;
	private Calendar endTime;
	private String subject; 
	@Type(type="text")
	private String description;
	@ManyToOne(fetch=FetchType.EAGER)
	private Doctor doctor;
	
	private boolean moreThanOneDay;
	private String color;
	
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

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public Calendar getEndTime() {
		return endTime;
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
	
	public void setMoreThanOneDay(boolean moreThanOneDay) {
		this.moreThanOneDay = moreThanOneDay;
	}

	public boolean isMoreThanOneDay() {
		return moreThanOneDay;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public String getDuration() {
		if(this.endTime == null || this.startTime == null) return "";
		
		long milisec = this.endTime.getTimeInMillis() - this.startTime.getTimeInMillis();
		int minutes = (int) (milisec/(1000 * 60));
		int hour = (int)(minutes/60);
		minutes = minutes % 60;
		
		return hour + ":" + minutes;
	}

	public static Schedule empty() {
		Schedule empty = new Schedule();
		return empty;
	}

}
