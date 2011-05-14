package br.com.easyclinica.actions;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.CID;
import br.com.easyclinica.domain.repositories.AllCIDs;

@Resource
public class CidController extends BaseController {
	private final AllCIDs allCIDs;
	
	public CidController(Result result, AllCIDs allCIDs) {
		super(result);
		
		this.allCIDs = allCIDs;
	}

	@Get
	@Path("/cid/_search")
	public void _search(String q) {
		List<CID> cids = allCIDs.search(q);
		
		result.include("cids", cids);
	}
	
}
