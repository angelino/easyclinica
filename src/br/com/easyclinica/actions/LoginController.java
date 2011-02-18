package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.services.Authentication;

@Resource
public class LoginController {

	private final Result result;
	private final Authentication auth;

	public LoginController(Authentication auth, Result result) {
		this.auth = auth;
		this.result = result;
	}
	
	@Get
	@Path("/login")
	public void login() { 
	}
	
	@Post
	@Path("/login")
	public void doLogin(String login, String password) { 
		if(auth.user(login, password)) {
			result.redirectTo(HomeController.class).dashboard();
		}
		else {
			result.include("errorLogin", true);
			result.redirectTo(LoginController.class).login();
		}
	}
	
}
