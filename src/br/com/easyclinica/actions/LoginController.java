package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Resource
public class LoginController {

	private final Result result;
	private final LoggedUser loggedUser;

	public LoginController(LoggedUser loggedUser, Result result) {
		this.loggedUser = loggedUser;
		this.result = result;
	}
	
	@Get
	@Path("/login")
	public void login() { 
	}
	
	@Post
	@Path("/login")
	public void doLogin() { 
		Clinic clinic = new Clinic();
		clinic.setDomain("easyclinica");
		
		Employee employee = new Employee();
		employee.setUser("x");
		
		loggedUser.set(clinic, employee);
		
		result.redirectTo(HomeController.class).dashboard();
	}
	

}
