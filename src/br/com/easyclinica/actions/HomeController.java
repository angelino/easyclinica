package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class HomeController {

	@Get
	@Path("/")
	public void dashboard(){}

	@Get
	@Path("/nao-autorizado")
	public void notAuthorized() {
	}
}
