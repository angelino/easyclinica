package br.com.easyclinica.domain.reports;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Component
@RequestScoped
public class FinancialReportBuilder {

	private final FinancialReportGenerator report;
	private Calendar startDate;
	private Calendar endDate;
	private Doctor doctor;
	private HealthCarePlan plan;
	private final LoggedUser loggedUser;

	public FinancialReportBuilder(FinancialReportGenerator report, LoggedUser loggedUser){
		this.report = report;
		this.loggedUser = loggedUser;
	}
	
	public FinancialReportBuilder withStartDate(Calendar startDate) {
		this.startDate = startDate;
		return this;
	}
	
	public FinancialReportBuilder withEndDate(Calendar endDate) {
		this.endDate = endDate;
		return this;
	}
	
	public FinancialReportBuilder withDoctor(Doctor doctor) {
		this.doctor = doctor;
		return this;
	}
	
	public FinancialReportBuilder withPlan(HealthCarePlan plan) {
		this.plan = plan;
		return this;
	}
	
	public Map<String, Object> config() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clinic", loggedUser.getClinic());
		params.put("start", startDate);
		params.put("end", endDate);
		params.put("doctor", doctor == null ? "Todos" : doctor.getName());
		params.put("plan", plan == null ? "Todos" : plan.getName());
		return params;
	}
	public List<Appointment> build() {
		return report.full(startDate, endDate, doctor, plan);
	}
}
