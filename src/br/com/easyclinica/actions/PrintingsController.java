package br.com.easyclinica.actions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.infra.multitenancy.LoggedUser;
import br.com.easyclinica.infra.reports.JasperMaker;

@Resource
public class PrintingsController {

	private final JasperMaker jasperMaker;
	private final AllPatients patients;
	private final LoggedUser loggedUser;

	public PrintingsController(JasperMaker jasperMaker, AllPatients patients, LoggedUser loggedUser) {
		this.jasperMaker = jasperMaker;
		this.patients = patients;
		this.loggedUser = loggedUser;
	}
	
	@Get
	@Path("/pacientes/{id}/impressos")
	public Patient index(int id) {
		return patients.getById(id);
	}
	
	@Get
	@Path("/pacientes/{id}/impressos/anamnese")
	public Download anamnese(int id) {
		Patient patient = patients.getById(id);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("patient", patient);
		params.put("clinic", loggedUser.getClinic());
		
		return jasperMaker.makePdf(  
	               "patient-anamnese",  
	               patient.getAnamneses(),   
	               "anamnese.pdf",   
	               true,   
	               params);
	}
	
	@Get
	@Path("/pacientes/{id}/impressos/atestado-de-saude")
	public Download atestadoDeSaude(int id) {
		Patient patient = patients.getById(id);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clinic", loggedUser.getClinic());
		
		return jasperMaker.makePdf(  
	               "atestadoDeSaude",  
	               Collections.singletonList(patient),   
	               "atestadoDeSaude.pdf",   
	               true,   
	               params);
	}
	
	@Get
	@Path("/pacientes/{id}/impressos/atestado-de-saude-ocupacional")
	public Download atestadoDeSaudeOcupacional(int id) {
		Patient patient = patients.getById(id);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clinic", loggedUser.getClinic());
		
		return jasperMaker.makePdf(  
	               "atestadoDeSaudeOcupacional",  
	               Collections.singletonList(patient),   
	               "atestadoDeSaudeOcupacional.pdf",   
	               true,   
	               params);
	}
}
