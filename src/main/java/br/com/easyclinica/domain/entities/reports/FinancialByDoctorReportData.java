package br.com.easyclinica.domain.entities.reports;

import java.math.BigDecimal;

import br.com.easyclinica.domain.entities.Doctor;

public class FinancialByDoctorReportData {

	private Doctor doctor;
	private int qtyAppointments;
	private BigDecimal procedureAmount;
	private BigDecimal appointmentAmount;
	private BigDecimal roomRateAmount;
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public int getQtyAppointments() {
		return qtyAppointments;
	}
	public void setQtyAppointments(int qtyAppointments) {
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

	
	
	
}
