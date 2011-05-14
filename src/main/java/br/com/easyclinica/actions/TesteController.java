package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class TesteController {

	@Get
	@Path("/erro")
	public void x() {
		throw new RuntimeException(new RuntimeException("que merda"));
	}
}
