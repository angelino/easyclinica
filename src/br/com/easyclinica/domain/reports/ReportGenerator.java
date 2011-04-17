package br.com.easyclinica.domain.reports;

import java.util.Calendar;
import java.util.List;

import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.reports.FinancialByDoctorReportData;

public interface ReportGenerator {
	List<Appointment> financial();
	List<FinancialByDoctorReportData> financialByDoctor(Calendar start, Calendar end);
}
