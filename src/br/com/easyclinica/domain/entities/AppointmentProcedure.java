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

import org.hibernate.annotations.Cascade;
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
	private boolean isFixedAmount;
	
	@Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="procedure")
	private List<AppointmentMaterial> materials;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="procedure")
	@Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	private List<AppointmentMedicine> medicines;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="procedure")
	@Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	private List<PrecifiedAssistant> assistants;
	
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
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
	
	public BigDecimal getTotalAmount() {
		BigDecimal total = (this.amount == null ? BigDecimal.ZERO : this.amount);
		
		for(AppointmentMaterial material : materials) {
			total = total.add(material.getTotalAmount());
		}
		for(AppointmentMedicine medicine : medicines) {
			total = total.add(medicine.getTotalAmount());
		}		
		return total;
	}
	public void setAssistants(List<PrecifiedAssistant> assistants) {
		this.assistants = assistants;
	}
	public List<PrecifiedAssistant> getAssistants() {
		return assistants;
	}
	
	
}
