package br.com.easyclinica.domain.reports;

import java.util.Calendar;
import java.util.List;

import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.reports.FinancialByDoctorReportData;
import br.com.easyclinica.domain.entities.reports.FinancialByHealthCarePlanReportData;

public interface FinancialReportGenerator {
	List<Appointment> full(Calendar startDate, Calendar endDate, Doctor doctor, HealthCarePlan plan);
	List<FinancialByDoctorReportData> byDoctor(Calendar startDate, Calendar endDate);
	List<FinancialByHealthCarePlanReportData> byHealthCarePlan(Calendar startDate, Calendar endDate);
}
