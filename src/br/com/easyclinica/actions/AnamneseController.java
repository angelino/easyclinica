package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.repositories.AllPatients;

@Resource
public class AnamneseController {

	private final AllPatients patients;

	public AnamneseController(AllPatients patients) {
		this.patients = patients;
	}
	
	@Get
	@Path("/pacientes/{patient}/anamneses")
	public Patient list(int patient) {
		return patients.getById(patient);
	}
}
