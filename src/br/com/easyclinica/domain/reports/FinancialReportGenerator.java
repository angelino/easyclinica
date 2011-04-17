package br.com.easyclinica.domain.reports;

import java.util.Calendar;
import java.util.List;

import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.HealthCarePlan;

public interface FinancialReportGenerator {
	List<Appointment> financial(Calendar startDate, Calendar endDate, Doctor doctor, HealthCarePlan plan);
}
