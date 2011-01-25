package br.com.easyclinica.domain.entities;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class AppointmentProcedure {

	private int id;
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="appointment_fk") 
	private Appointment appointment;
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="procedure_fk") 
	private Procedure procedure;
	private double amount;
	private boolean isFixedAmount;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="procedure") 
	private List<AppointmentMaterial> materials;
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="procedure")
	private List<AppointmentMedicine> medicines;
	
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public Procedure getProcedure() {
		return procedure;
	}
	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public boolean isFixedAmount() {
		return isFixedAmount;
	}
	
	public List<AppointmentMaterial> getMaterials() {
		return Collections.unmodifiableList(materials);
	}
	public List<AppointmentMedicine> getMedicines() {
		return Collections.unmodifiableList(medicines);
	}
	
	public int getId() {
		return id;
	}
	
	
}
