package br.com.easyclinica.domain.reports;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Receipt;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Component
public class ReceiptReportBuilder {
	private final ReceiptReportGenerator report;
	private Calendar startDate;
	private Calendar endDate;
	private final LoggedUser loggedUser;
	
	public ReceiptReportBuilder(ReceiptReportGenerator report, LoggedUser loggedUser){
		this.report = report;
		this.loggedUser = loggedUser;
	}

	public ReceiptReportBuilder withStartDate(Calendar startDate) {
		this.startDate = startDate;
		return this;
	}
	
	public ReceiptReportBuilder withEndDate(Calendar endDate) {
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
	public List<Receipt> build() {
		return report.generate(startDate, endDate);
	}
}
