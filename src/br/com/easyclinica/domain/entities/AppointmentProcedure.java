package br.com.easyclinica.domain.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class AppointmentProcedure {

	@Id @GeneratedValue
	private int id;
	@ManyToOne(fetch=FetchType.LAZY) 
	private Appointment appointment;
	@ManyToOne(fetch=FetchType.LAZY) 
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
		return materials;
	}
	public List<AppointmentMedicine> getMedicines() {
		return medicines;
	}
	
	public int getId() {
		return id;
	}
	public void setFixedAmount(boolean isFixedAmount) {
		this.isFixedAmount = isFixedAmount;
	}
	public void setMaterials(List<AppointmentMaterial> materials) {
		this.materials = materials;
	}
	public void setMedicines(List<AppointmentMedicine> medicines) {
		this.medicines = medicines;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}