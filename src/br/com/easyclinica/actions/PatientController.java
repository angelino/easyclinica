package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.view.paginator.Paginator;

@Resource
public class PatientController {

	private final Result result;
	private final AllPatients allPatients;
	private final Paginator paginator;

	public PatientController(AllPatients allPatients, Result result, Paginator paginator) {
		this.allPatients = allPatients;
		this.result = result;
		this.paginator = paginator;
	}

	@Get
	@Path("/pacientes")
	public void index(int page) {
		int currentPage = page == 0 ? Paginator.firstPage() : page;
		result.include("patients", paginator.paginate(allPatients, currentPage));
	}

}
