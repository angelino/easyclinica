package br.com.easyclinica.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.Normalizer;


import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.InputStreamDownload;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.services.PricingSheetExporter;

@Resource
public class PricesController {

	private final PricingSheetExporter exporter;
	private final AllHealthCarePlans plans;
	public PricesController(PricingSheetExporter exporter, AllHealthCarePlans plans) {
		this.exporter = exporter;
		this.plans = plans;
	}
	
	@Get
	@Path("/convenios/{id}/precos")
	public Download priceList(int id) throws IOException {

		HealthCarePlan plan = plans.getById(id); 
	    
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		exporter.priceList(plan, out);
		
	    return new InputStreamDownload(
	    		new ByteArrayInputStream(out.toByteArray()),
        		"application/ms-excel",
        		"precos-"+formatName(plan.getName())+"-" + id+".xls",
        		true,
        		out.size());
	}

	private String formatName(String name) {
		return Normalizer.normalize(name.replace(" ", ""), java.text.Normalizer.Form.NFD);
	}
}
