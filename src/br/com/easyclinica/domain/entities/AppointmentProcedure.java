package br.com.easyclinica.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.easyclinica.domain.types.Money;

@Entity
public class AppointmentProcedure {

	@Id @GeneratedValue
	private int id;
	@ManyToOne(fetch=FetchType.LAZY) 
	private Appointment appointment;
	@ManyToOne(fetch=FetchType.EAGER) 
	private Procedure procedure;
	@Embedded private Money amount;
	private boolean isFixedAmount;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="procedure") 
	private List<AppointmentMaterial> materials;
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="procedure")
	private List<AppointmentMedicine> medicines;
	
	public AppointmentProcedure() {
		materials = new ArrayList<AppointmentMaterial>();
		medicines = new ArrayList<AppointmentMedicine>();
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
	public Money getAmount() {
		return amount;
	}
	public void setAmount(Money amount) {
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
	
	public Money getTotalAmount() {
		Money total = (this.amount == null ? Money.empty() : this.amount);
		
		for(AppointmentMaterial material : materials) {
			total.plus(material.getTotalAmount().getAmount());
		}
		for(AppointmentMedicine medicine : medicines) {
			total.plus(medicine.getTotalAmount().getAmount());
		}		
		return total;
	}
	
	
}
