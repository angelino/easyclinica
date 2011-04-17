package br.com.easyclinica.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.easyclinica.domain.reports.FinancialByDoctorReportBuilder;
import br.com.easyclinica.domain.reports.FinancialReportBuilder;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.infra.multitenancy.LoggedUser;
import br.com.easyclinica.infra.reports.JasperMaker;

@Resource
public class ReportsController {

	private final JasperMaker jasperMaker;
	private final AllDoctors doctors;
	private final AllHealthCarePlans plans;
	private final FinancialReportBuilder reportBuilder;
	private final FinancialByDoctorReportBuilder financialByDoctorReportBuilder;
	private final Result result;

	public ReportsController(JasperMaker jasperMaker,
			FinancialReportBuilder reportBuilder, AllHealthCarePlans plans,
			AllDoctors doctors, LoggedUser loggedUser, Result result, FinancialByDoctorReportBuilder financialByDoctorReportBuilder) {
		this.jasperMaker = jasperMaker;
		this.reportBuilder = reportBuilder;
		this.plans = plans;
		this.doctors = doctors;
		this.result = result;
		this.financialByDoctorReportBuilder = financialByDoctorReportBuilder;
	}

	@Get
	@Path("/relatorios")
	public void index() { 
		result.include("doctors", doctors.get());
		result.include("plans", plans.get());
	}
	
	@Get
	@Path("/relatorios/financeiro/{start}/{end}/{planId}/{doctorId}")
	public Download financial(String start, String end, int planId, int doctorId) throws ParseException {

		reportBuilder
			.withStartDate(convertToStartDate(start))
			.withEndDate(convertToEndDate(end));
		
		if(doctorId > 0) reportBuilder.withDoctor(doctors.getById(doctorId));
		if(planId > 0) reportBuilder.withPlan(plans.getById(planId));
		
		return jasperMaker.makePdf("financialReport", reportBuilder.build(),
				"financeiro.pdf", true, reportBuilder.config());

	}

	private Calendar convertToEndDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(sdf.parse(date));
		startDate.set(Calendar.HOUR_OF_DAY, 23);
		startDate.set(Calendar.MINUTE, 59);
		startDate.set(Calendar.SECOND, 59);
		return startDate;
	}

	private Calendar convertToStartDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(sdf.parse(date));
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		return startDate;
	}
	
	@Get
	@Path("/relatorios/financeiro/medicos")
	public Download financialByDoctor(Calendar start, Calendar end) {
		setToTime(start,0,0,0);
		setToTime(end,23,59,59);
		
		financialByDoctorReportBuilder.withStartDate(start)
					 				  .withEndDate(end);
		
		return jasperMaker.makePdf("financialByDoctorReport", financialByDoctorReportBuilder.build(),
				"financeiro_por_medico.pdf", true, financialByDoctorReportBuilder.config());
	}
	
	private void setToTime(Calendar date, int hour, int minute, int second) {
		date.set(Calendar.HOUR_OF_DAY,hour);
		date.set(Calendar.MINUTE,minute);
		date.set(Calendar.SECOND, second);
	}
}
