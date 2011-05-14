package br.com.easyclinica.domain.services.pricing.exporter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.easyclinica.domain.entities.HealthCarePlan;

@Component
@RequestScoped
public class PricingSheetExporterBuilder {

	private HSSFWorkbook wb;
	private HealthCarePlan plan;
	private final List<StuffExporter> exporters;

	public PricingSheetExporterBuilder(List<StuffExporter> exporters) {
		this.exporters = exporters;
	}
	
	public PricingSheetExporterBuilder start() {
		this.wb = new HSSFWorkbook();
		return this;
	}
	
	public PricingSheetExporterBuilder toPlan(HealthCarePlan plan){
		this.plan = plan;
		return this;
	}

	public PricingSheetExporterBuilder putMaterials(boolean yes) {
		if(yes) find("material").export(plan, wb);
		return this;
	}
	
	public PricingSheetExporterBuilder putSpecialties(boolean yes) {
		if(yes) find("specialty").export(plan, wb);
		return this;
	}
	
	public PricingSheetExporterBuilder putProcedures(boolean yes) {
		if(yes) find("procedure").export(plan, wb);
		return this;
	}
	public PricingSheetExporterBuilder putMedicines(boolean yes) {
		if(yes) find("medicine").export(plan, wb);
		return this;
	}
	
	private StuffExporter find(String whichOne) {
		for(StuffExporter exporter : exporters) {
			if(exporter.getName().equals(whichOne)) return exporter;
		}
		
		throw new IllegalArgumentException("invalid entity to export");
	}
	
	public void write(ByteArrayOutputStream fileOut) {
		try {
			wb.write(fileOut);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
