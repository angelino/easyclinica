package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Index;

@Entity
public class AppointmentProcedure {

	@Id @GeneratedValue
	private int id;
	@ManyToOne(fetch=FetchType.LAZY) @Index(name="appProcedureIndex")
	private Appointment appointment;
	@ManyToOne(fetch=FetchType.EAGER) 
	private Procedure procedure;
	private BigDecimal amount;
	private int ch;
	private BigDecimal materialAmount;
	private BigDecimal medicineAmount;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="procedure")
	private List<AppointmentMaterial> materials;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="procedure")
	private List<AppointmentMedicine> medicines;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="procedure")
	private List<AppointmentAssistant> assistants;
	
	public AppointmentProcedure() {
		materials = new ArrayList<AppointmentMaterial>();
		medicines = new ArrayList<AppointmentMedicine>();
		assistants = new ArrayList<AppointmentAssistant>();
	}
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public boolean isFixedAmount() {
		return ch==0;
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
	protected void setMaterials(List<AppointmentMaterial> materials) {
		this.materials = materials;
	}
	protected void setMedicines(List<AppointmentMedicine> medicines) {
		this.medicines = medicines;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public void addMaterial(AppointmentMaterial material) {
		materials.add(material);
		
	}
	public void addMedicine(AppointmentMedicine medicine) {
		medicines.add(medicine);		
	}
	
	public BigDecimal getTotalAmount() {
		BigDecimal total = (this.amount == null ? BigDecimal.ZERO : this.amount);
		
		total = total.add(getMaterialsTotal());		
		total = total.add(getMedicinesTotal());
		total = total.add(getAssistantsTotal());		
		
		return total;
	}
	public BigDecimal getMaterialsTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for(AppointmentMaterial material : materials) {
			total = total.add(material.getTotalAmount());
		}
		
		return total;
	}
	
	public BigDecimal getMedicinesTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for(AppointmentMedicine medicine : medicines) {
			total = total.add(medicine.getTotalAmount());
		}
		
		return total;
	}
	
	public BigDecimal getAssistantsTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for(AppointmentAssistant assistant : assistants) {
			total = total.add(assistant.getAmount());
		}
		
		return total;
	}
	
	public void setAssistants(List<AppointmentAssistant> assistants) {
		this.assistants = assistants;
	}
	public List<AppointmentAssistant> getAssistants() {
		return assistants;
	}
	
	public int getCh() {
		return ch;
	}
	public void setCh(int ch) {
		this.ch = ch;
	}
	
	public void setMaterialAmount(BigDecimal materialAmount) {
		this.materialAmount = materialAmount;
	}
	public BigDecimal getMaterialAmount() {
		return materialAmount;
	}
	
	public void setMedicineAmount(BigDecimal medicineAmount) {
		this.medicineAmount = medicineAmount;
	}
	public BigDecimal getMedicineAmount() {
		return medicineAmount;
	}

	
	
}
