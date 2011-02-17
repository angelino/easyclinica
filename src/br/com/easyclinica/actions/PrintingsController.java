package br.com.easyclinica.actions;

import java.util.HashMap;
import java.util.Map;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.infra.reports.JasperMaker;

@Resource
public class PrintingsController {

	private final JasperMaker jasperMaker;
	private final AllPatients patients;

	public PrintingsController(JasperMaker jasperMaker, AllPatients patients) {
		this.jasperMaker = jasperMaker;
		this.patients = patients;
	}
	
	@Get
	@Path("/pacientes/{id}/impressos/anamnese")
	public Download anamnese(int id) {
		Patient patient = patients.getById(id);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("patientName", patient.getName());
		
		return jasperMaker.makePdf(  
	               "patient-anamnese.jrxml",  
	               patient.getAnamneses(),   
	               "anamnese.pdf",   
	               true,   
	               params);  
	}
}
