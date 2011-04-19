package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.repositories.ClinicInfo;
import br.com.easyclinica.infra.multitenancy.LoggedUser;
import br.com.easyclinica.view.Messages;

@Resource
public class ClinicController extends BaseController {

	private final LoggedUser loggedUser;
	private final ClinicInfo clinicInfo;
	
	public ClinicController(LoggedUser loggedUser, Result result, ClinicInfo clinicInfo) {
		super(result);
		this.loggedUser = loggedUser;
		this.clinicInfo = clinicInfo;
	}
	
	@Get
	@Path("/clinica")
	public void index() {
		result.include("clinic", loggedUser.getClinic());
	}
	
	@Post
	@Path("/clinica")
	public void save(Clinic clinic) {
		clinicInfo.update(clinic);
		loggedUser.set(clinicInfo.get(), loggedUser.getEmployee());
		
		successMsg(Messages.CLINIC_UPDATED);
		result.redirectTo(this).index();
	}
}
