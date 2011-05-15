package br.com.easyclinica.actions;

import java.util.Calendar;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.easyclinica.domain.reports.FinancialByDoctorReportBuilder;
import br.com.easyclinica.domain.reports.FinancialByHealthCarePlanReportBuilder;
import br.com.easyclinica.domain.reports.FinancialReportBuilder;
import br.com.easyclinica.domain.reports.ReceiptReportBuilder;
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
	private final FinancialByDoctorReportBuilder doctorBuilder;
	private final Result result;
	private final FinancialByHealthCarePlanReportBuilder planBuilder;
	private final ReceiptReportBuilder receiptBuilder;

	public ReportsController(
			JasperMaker jasperMaker,
			FinancialReportBuilder reportBuilder,
			AllHealthCarePlans plans,
			AllDoctors doctors,
			LoggedUser loggedUser,
			Result result,
			FinancialByDoctorReportBuilder financialByDoctorReportBuilder,
			FinancialByHealthCarePlanReportBuilder financialByHealthCarePlanBuilder,
			ReceiptReportBuilder receiptBuilder) {
		this.jasperMaker = jasperMaker;
		this.reportBuilder = reportBuilder;
		this.plans = plans;
		this.doctors = doctors;
		this.result = result;
		this.doctorBuilder = financialByDoctorReportBuilder;
		this.planBuilder = financialByHealthCarePlanBuilder;
		this.receiptBuilder = receiptBuilder;
	}

	@Get
	@Path("/relatorios")
	public void index() {
		result.include("doctors", doctors.get());
		result.include("plans", plans.get());
	}

	@Get
	@Path("/relatorios/financeiro")
	public Download financial(Calendar start, Calendar end, int planId, int doctorId) {

		reportBuilder.withStartDate(convertToStartDate(start)).withEndDate(
				convertToEndDate(end));

		if (doctorId > 0)
			reportBuilder.withDoctor(doctors.getById(doctorId));
		if (planId > 0)
			reportBuilder.withPlan(plans.getById(planId));

		return jasperMaker.makePdf("financialReport", reportBuilder.build(),
				"financeiro.pdf", true, reportBuilder.config());

	}

	@Get
	@Path("/relatorios/financeiro/medicos")
	public Download financialByDoctor(Calendar start, Calendar end) {
		doctorBuilder.withStartDate(convertToStartDate(start)).withEndDate(convertToEndDate(end));

		return jasperMaker.makePdf("financialByDoctorReport",
				doctorBuilder.build(), "financeiro_por_medico.pdf", true,
				doctorBuilder.config());
	}

	@Get
	@Path("/relatorios/financeiro/convenios")
	public Download financialByHealthCarePlan(Calendar start, Calendar end) {

		planBuilder.withStartDate(convertToStartDate(start)).withEndDate(convertToEndDate(end));

		return jasperMaker.makePdf("financialByHealthCarePlanReport",
				planBuilder.build(), "financeiro_por_convenio.pdf", true,
				planBuilder.config());
	}
	
	@Get
	@Path("/relatorios/recibos") 
	public Download receipts(Calendar start, Calendar end) {
		receiptBuilder.withStartDate(convertToStartDate(start)).withEndDate(convertToEndDate(end));

		return jasperMaker.makePdf("receiptReport",
				receiptBuilder.build(), "relatorio_de_recibos.pdf", true,
				receiptBuilder.config());
	}
	
	private Calendar convertToEndDate(Calendar date) {
		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);
		return date;
	}
	
	private Calendar convertToStartDate(Calendar date) {
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		return date;
	}
}
