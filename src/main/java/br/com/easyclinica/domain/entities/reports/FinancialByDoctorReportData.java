package br.com.easyclinica.domain.entities.reports;

import java.math.BigDecimal;

import br.com.easyclinica.domain.entities.Doctor;

public class FinancialByDoctorReportData {

	private Doctor doctor;
	private long qtyAppointments;
	private BigDecimal procedureAmount;
	private BigDecimal appointmentAmount;
	private BigDecimal roomRateAmount;
	private BigDecimal materialAmount;
	private BigDecimal medicineAmount;
	private BigDecimal assistantAmount;

	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public long getQtyAppointments() {
		return qtyAppointments;
	}
	public void setQtyAppointments(long qtyAppointments) {
		this.qtyAppointments = qtyAppointments;
	}
	public BigDecimal getProcedureAmount() {
		return procedureAmount;
	}
	public void setProcedureAmount(BigDecimal procedureAmount) {
		this.procedureAmount = procedureAmount;
	}
	public BigDecimal getAppointmentAmount() {
		return appointmentAmount;
	}
	public void setAppointmentAmount(BigDecimal appointmentAmount) {
		this.appointmentAmount = appointmentAmount;
	}
	public BigDecimal getRoomRateAmount() {
		return roomRateAmount;
	}
	public void setRoomRateAmount(BigDecimal roomRateAmount) {
		this.roomRateAmount = roomRateAmount;
	}
	public BigDecimal getMaterialAmount() {
		return materialAmount;
	}
	public void setMaterialAmount(BigDecimal materialAmount) {
		this.materialAmount = materialAmount;
	}
	public BigDecimal getMedicineAmount() {
		return medicineAmount;
	}
	public void setMedicineAmount(BigDecimal medicineAmount) {
		this.medicineAmount = medicineAmount;
	}
	public BigDecimal getAssistantAmount() {
		return assistantAmount;
	}
	public void setAssistantAmount(BigDecimal assistantAmount) {
		this.assistantAmount = assistantAmount;
	}

	
	
	
}
