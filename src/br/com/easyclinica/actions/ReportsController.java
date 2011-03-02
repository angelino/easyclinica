package br.com.easyclinica.actions;

import java.util.HashMap;
import java.util.Map;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.easyclinica.domain.reports.ReportGenerator;
import br.com.easyclinica.infra.multitenancy.LoggedUser;
import br.com.easyclinica.infra.reports.JasperMaker;

@Resource
public class ReportsController {

	private final JasperMaker jasperMaker;
	private final LoggedUser loggedUser;
	private final ReportGenerator reports;

	public ReportsController(JasperMaker jasperMaker, LoggedUser loggedUser, ReportGenerator reports) {
		this.jasperMaker = jasperMaker;
		this.loggedUser = loggedUser;
		this.reports = reports;
	}
	
	@Get
	@Path("/relatorios/financeiro")
	public Download financial() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clinic", loggedUser.getClinic());
		
		return jasperMaker.makePdf("financialReport", reports.financial(), "financeiro.pdf", true, params);
		
	}
}
