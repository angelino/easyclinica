package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class AllSchedulesController extends BaseController {

	public AllSchedulesController(Result result) {
		super(result);
	}

	
	@Get
	@Path("/agenda")
	public void index() {
		
	}
}
