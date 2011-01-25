package br.com.easyclinica.view.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AppointmentData {

	private Calendar date;
	private int doctor;
	private int healthCarePlan;
	private int specialty;
	private int patient;
	private boolean isReturn;
	
	private List<ProcedureData> procedures;

	public AppointmentData() {
		procedures = new ArrayList<ProcedureData>();	
	}
	
	public int getPatient() {
		return patient;
	}

	public void setPatient(int patient) {
		this.patient = patient;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public int getDoctor() {
		return doctor;
	}

	public void setDoctor(int doctor) {
		this.doctor = doctor;
	}

	public int getHealthCarePlan() {
		return healthCarePlan;
	}

	public void setHealthCarePlan(int healthCarePlan) {
		this.healthCarePlan = healthCarePlan;
	}

	public int getSpecialty() {
		return specialty;
	}

	public void setSpecialty(int specialty) {
		this.specialty = specialty;
	}

	public boolean isReturn() {
		return isReturn;
	}

	public void setReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}

	public List<ProcedureData> getProcedures() {
		return procedures;
	}

	public void setProcedures(List<ProcedureData> procedures) {
		this.procedures = procedures;
	}
	
	
	
	
}
