package br.com.easyclinica.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.InputStreamDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.services.pricing.PricingSheetExporter;
import br.com.easyclinica.domain.services.pricing.PricingSheetImporter;
import br.com.easyclinica.domain.services.pricing.PricingUpdater;

@Resource
public class PricesController {

	private final PricingSheetExporter exporter;
	private final AllHealthCarePlans plans;
	private final Result result;
	private final PricingSheetImporter importer;
	private final PricingUpdater updater;

	public PricesController(PricingSheetExporter exporter,
			PricingSheetImporter importer, AllHealthCarePlans plans,
			PricingUpdater updater,
			Result result) {
		this.exporter = exporter;
		this.importer = importer;
		this.plans = plans;
		this.updater = updater;
		this.result = result;
	}
	
	@Get
	@Path("/convenios/{id}/precos")
	public Download priceList(int id) throws IOException {

		HealthCarePlan plan = plans.getById(id);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		exporter.priceList(plan, out);

		return new InputStreamDownload(new ByteArrayInputStream(
				out.toByteArray()), "application/ms-excel", "precos-"
				+ formatName(plan.getName()) + ".xls", true,
				out.size());
	}

	@Post
	@Path("/convenios/{id}/precos")
	public void importPrices(int id, List<ImportedStuff> procedures,
			List<ImportedStuff> specialties, List<ImportedStuff> medicines,
			List<ImportedStuff> materials) {
		
		HealthCarePlan plan = plans.getById(id);
		updater.pricesForAHealthCarePlan(plan, procedures, specialties, medicines, materials);
	}

	@Get
	@Path("/convenios/{id}/precos/importar")
	public void showFileUpload(int id) {
		result.include("id", id);
	}

	@Post
	@Path("/convenios/{id}/precos/confirmar")
	public void confirmImport(int id, UploadedFile file) {
		importer.excel(file.getFile());

		result.include("import", importer);
		result.include("id", id);
	}

	private String formatName(String name) {
		return Normalizer.normalize(name.replace(" ", ""),
				java.text.Normalizer.Form.NFD);
	}
}
