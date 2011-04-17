package br.com.easyclinica.domain.reports;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.easyclinica.domain.entities.reports.FinancialByDoctorReportData;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Component
@RequestScoped
public class FinancialByDoctorReportBuilder {
	private final ReportGenerator report;
	private Calendar startDate;
	private Calendar endDate;
	private final LoggedUser loggedUser;
	
	public FinancialByDoctorReportBuilder(ReportGenerator report, LoggedUser loggedUser){
		this.report = report;
		this.loggedUser = loggedUser;
	}

	public FinancialByDoctorReportBuilder withStartDate(Calendar startDate) {
		this.startDate = startDate;
		return this;
	}
	
	public FinancialByDoctorReportBuilder withEndDate(Calendar endDate) {
		this.endDate = endDate;
		return this;
	}
	
	public Map<String, Object> config() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clinic", loggedUser.getClinic());
		params.put("start", startDate);
		params.put("end", endDate);
		return params;
	}
	public List<FinancialByDoctorReportData> build() {
		return report.financialByDoctor(startDate, endDate);
	}
}
