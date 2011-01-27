package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.infra.multitenancy.Tenant;

@Resource
public class LoginController {

	private final Tenant tenant;
	private final Result result;

	public LoginController(Tenant tenant, Result result) {
		this.tenant = tenant;
		this.result = result;
	}
	
	@Get
	@Path("/login")
	public void login() { }
	
	@Post
	@Path("/login")
	public void doLogin() { 
		Clinic clinic = new Clinic();
		clinic.setDomain("easyclinica");
		
		Employee employee = new Employee();
		employee.setUser("x");
		
		tenant.set(clinic, employee);
		
		result.redirectTo(HomeController.class).dashboard();
	}
	

}
