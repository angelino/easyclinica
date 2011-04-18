package br.com.easyclinica.domain.entities.reports;

import java.math.BigDecimal;

import br.com.easyclinica.domain.entities.HealthCarePlan;

public class FinancialByHealthCarePlanReportData {
	
	private HealthCarePlan healthCarePlan;
	private int qtyAppointments;
	private BigDecimal procedureAmount;
	private BigDecimal appointmentAmount;
	private BigDecimal roomRateAmount;
	public HealthCarePlan getHealthCarePlan() {
		return healthCarePlan;
	}
	public void setHealthCarePlan(HealthCarePlan healthCarePlan) {
		this.healthCarePlan = healthCarePlan;
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
