package br.com.easyclinica.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.ArrayList;
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
import br.com.easyclinica.domain.services.pricing.PricingSheetImporter;
import br.com.easyclinica.domain.services.pricing.exporter.PricingSheetExporterBuilder;
import br.com.easyclinica.domain.services.pricing.update.PricingUpdater;

@Resource
public class PricesController {

	private final PricingSheetExporterBuilder exporter;
	private final AllHealthCarePlans plans;
	private final Result result;
	private final PricingSheetImporter importer;
	private final PricingUpdater updater;

	public PricesController(PricingSheetExporterBuilder exporter,
			PricingSheetImporter importer, AllHealthCarePlans plans,
			PricingUpdater updater, Result result) {
		this.exporter = exporter;
		this.importer = importer;
		this.plans = plans;
		this.updater = updater;
		this.result = result;
	}

	@Get
	@Path("/convenios/{id}/financeiro")
	public void financial(int id) {
		result.include("healthCarePlan", plans.getById(id));
	}

	@Get
	@Path("/convenios/{id}/precos")
	public Download priceList(int id, String filter) throws IOException {

		HealthCarePlan plan = plans.getById(id);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		exporter.start().toPlan(plan).filterBy(filter).write(out);
		
		return new InputStreamDownload(new ByteArrayInputStream(
				out.toByteArray()), "application/ms-excel", "precos-"
				+ formatName(plan.getName()) + ".xls", true, out.size());
	}

	@Post
	@Path("/convenios/{id}/precos")
	public void importPrices(int id, String proceduresId, String proceduresCh, String proceduresRoomTax, String proceduresValue,
			String materialsId, String materialsValue,
			String medicinesId, String medicinesValue,
			String specialtiesId, String specialtiesValue) {

		HealthCarePlan plan = plans.getById(id);

		List<ImportedStuff> procedures = convert(proceduresId, proceduresCh, proceduresRoomTax, proceduresValue);
		List<ImportedStuff> specialties = convert(specialtiesId, specialtiesValue);
		List<ImportedStuff> medicines = convert(medicinesId, medicinesValue);
		List<ImportedStuff> materials = convert(materialsId, materialsValue);

		updater.pricesForAHealthCarePlan(plan, procedures, specialties,
				medicines, materials);

		result.include("healthCarePlan", plan);
	}

	private List<ImportedStuff> convert(String proceduresId,
			String proceduresCh, String proceduresRoomTax,
			String proceduresValue) {
		List<ImportedStuff> list = new ArrayList<ImportedStuff>();
		
		if(proceduresId.length()>0) {
			String[] ids = proceduresId.split(",");
			String[] values = proceduresValue.split(",");
			String[] chs = proceduresCh.split(",");
			String[] roomTaxes = proceduresRoomTax.split(",");
			
			for(int i = 0; i < ids.length; i++) {
				list.add(new ImportedStuff(Integer.parseInt(ids[i]), "", new BigDecimal(values[i]), 
						Integer.parseInt(chs[i]), new BigDecimal(roomTaxes[i])));
			}
		}
		
		return list;
	}

	private List<ImportedStuff> convert(String idFromView,
			String valueFromView) {
		List<ImportedStuff> list = new ArrayList<ImportedStuff>();
		
		if(idFromView.length()>0) {
			String[] ids = idFromView.split(",");
			String[] values = valueFromView.split(",");
			
			for(int i = 0; i < ids.length; i++) {
				list.add(new ImportedStuff(Integer.parseInt(ids[i]), "", new BigDecimal(values[i])));
			}
		}
		
		return list;
	}

	@Get
	@Path("/convenios/{id}/precos/importar")
	public void showFileUpload(int id) {
		result.include("id", id);
		result.include("healthCarePlan", plans.getById(id));
	}

	@Post
	@Path("/convenios/{id}/precos/confirmar")
	public void confirmImport(int id, UploadedFile file) {
		importer.excel(file.getFile(), plans.getById(id));

		result.include("import", importer);
		result.include("id", id);
		result.include("healthCarePlan", plans.getById(id));
	}

	private String formatName(String name) {
		return Normalizer.normalize(name.replace(" ", ""),
				java.text.Normalizer.Form.NFD);
	}
}
