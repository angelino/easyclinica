package br.com.easyclinica.domain.services.pricing;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.pricing.ImportedStuff;

@Component
public class PricingSheetImporter {

	private List<ImportedStuff> importedSpecialties;
	private List<ImportedStuff> importedProcedures;
	private List<ImportedStuff> importedMaterials;
	private List<ImportedStuff> importedMedicines;
	private HSSFWorkbook wb;

	public PricingSheetImporter() {
		this.importedSpecialties = new ArrayList<ImportedStuff>();
		this.importedProcedures = new ArrayList<ImportedStuff>();
		this.importedMaterials = new ArrayList<ImportedStuff>();
		this.importedMedicines = new ArrayList<ImportedStuff>();
	}

	public void excel(InputStream is, HealthCarePlan plan) {
		try {
			wb = new HSSFWorkbook(is);

			extractSpecialties(plan);
			extractMaterials(plan);
			extractMedicines(plan);
			extractProcedures(plan);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private void extractSpecialties(HealthCarePlan plan) {
		if (wb.getSheet("Especialidades") != null) {
			extract(wb.getSheet("Especialidades"), importedSpecialties);
		}
		
		Map<Integer, PrecifiedSpecialty> map = generateIndexForSpecialties(plan);
		Iterator<ImportedStuff> it = importedSpecialties.iterator();
		while(it.hasNext()) {
			ImportedStuff importedSpecialty = it.next();
			PrecifiedSpecialty precifiedSpecialty = map.get(importedSpecialty.getId());
			if(precifiedSpecialty != null && precifiedSpecialty.getAmount().doubleValue() == importedSpecialty.getValue().doubleValue()) {
				it.remove();
			}
		}
	}

	private Map<Integer, PrecifiedSpecialty> generateIndexForSpecialties(HealthCarePlan plan) {
		Map<Integer, PrecifiedSpecialty> map = new HashMap<Integer, PrecifiedSpecialty>();
		for(PrecifiedSpecialty s : plan.getPrecifiedSpecialties()){
			map.put(s.getSpecialty().getId(), s);
		}
		return map;
	}

	private void extractMaterials(HealthCarePlan plan) {
		if (wb.getSheet("Materiais") != null) {
			extract(wb.getSheet("Materiais"), importedMaterials);
		}
		
		Map<Integer, PrecifiedMaterial> map = generateIndexForMaterials(plan);
		
		Iterator<ImportedStuff> it = importedMaterials.iterator();
		while(it.hasNext()) {
			ImportedStuff importedMaterial = it.next();
			PrecifiedMaterial precifiedMaterial = map.get(importedMaterial.getId());
			
			// TODO: OU precifiedMaterial == null && importedMaterial tudo zerado, remove tb
			if(precifiedMaterial != null && precifiedMaterial.getAmount().doubleValue() == importedMaterial.getValue().doubleValue()) {
				it.remove();
			}
		}
	}
	
	private Map<Integer, PrecifiedMaterial> generateIndexForMaterials(HealthCarePlan plan) {
		Map<Integer, PrecifiedMaterial> map = new HashMap<Integer, PrecifiedMaterial>();
		for(PrecifiedMaterial s : plan.getPrecifiedMaterials()){
			map.put(s.getMaterial().getId(), s);
		}
		return map;
	}

	private void extractMedicines(HealthCarePlan plan) {
		if (wb.getSheet("Medicamentos") != null) {
			extract(wb.getSheet("Medicamentos"), importedMedicines);
		}
		
		Map<Integer, PrecifiedMedicine> map = generateIndexForMedicines(plan);
		Iterator<ImportedStuff> it = importedMedicines.iterator();
		while(it.hasNext()) {
			ImportedStuff importedMedicine = it.next();
			PrecifiedMedicine precifiedMedicine = map.get(importedMedicine.getId());
			if(precifiedMedicine != null && precifiedMedicine.getAmount().doubleValue() == importedMedicine.getValue().doubleValue()) {
				it.remove();
			}
		}
	}
	
	private Map<Integer, PrecifiedMedicine> generateIndexForMedicines(HealthCarePlan plan) {
		Map<Integer, PrecifiedMedicine> map = new HashMap<Integer, PrecifiedMedicine>();
		for(PrecifiedMedicine s : plan.getPrecifiedMedicines()){
			map.put(s.getMedicine().getId(), s);
		}
		return map;
	}

	private void extractProcedures(HealthCarePlan plan) {
		if (wb.getSheet("Procedimentos") != null) {
			extractProcedure(wb.getSheet("Procedimentos"), importedProcedures);
		}
		
		Map<Integer, PrecifiedProcedure> map = generateIndexForProcedures(plan);
		Iterator<ImportedStuff> it = importedProcedures.iterator();
		while(it.hasNext()) {
			ImportedStuff importedProcedure = it.next();
			PrecifiedProcedure precifiedProcedure = map.get(importedProcedure.getId());
			if(precifiedProcedure != null && precifiedProcedure.getFixedAmount().doubleValue() == importedProcedure.getValue().doubleValue() && precifiedProcedure.getRoomTaxAmount().doubleValue() == importedProcedure.getRoomTax().doubleValue()
					&& precifiedProcedure.getCh() == importedProcedure.getCh()) {
				it.remove();
			}
		}
	}
	
	private Map<Integer, PrecifiedProcedure> generateIndexForProcedures(HealthCarePlan plan) {
		Map<Integer, PrecifiedProcedure> map = new HashMap<Integer, PrecifiedProcedure>();
		for(PrecifiedProcedure s : plan.getPrecifiedProcedures()){
			map.put(s.getProcedure().getId(), s);
		}
		return map;
	}

	private void extractProcedure(Sheet sheet, List<ImportedStuff> list) {
		int qty = 0;
		for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext();) {
			Row row = rit.next();
			qty++;
			if (qty == 1)
				continue;

			list.add(new ImportedStuff(toInt(row, 0), toString(row, 2),
					toBigDecimal(row, 3), toInt(row, 4), toBigDecimal(row, 5)));
		}
	}

	private void extract(Sheet sheet, List<ImportedStuff> list) {
		int qty = 0;
		for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext();) {
			Row row = rit.next();
			qty++;
			if (qty == 1)
				continue;

			list.add(new ImportedStuff(toInt(row, 0), toString(row, 1),
					toBigDecimal(row, 2)));
		}
	}

	private String toString(Row row, int namePosition) {
		return row.getCell(namePosition).getStringCellValue();
	}

	private int toInt(Row row, int position) {
		return (int) (row.getCell(position).getNumericCellValue());
	}

	private BigDecimal toBigDecimal(Row row, int pricePosition) {
		double numericCellValue = row.getCell(pricePosition)
				.getNumericCellValue();
		return new BigDecimal(String.valueOf(numericCellValue));
	}

	public List<ImportedStuff> getImportedSpecialties() {
		return importedSpecialties;
	}

	public List<ImportedStuff> getImportedProcedures() {
		return importedProcedures;
	}

	public List<ImportedStuff> getImportedMaterials() {
		return importedMaterials;
	}

	public List<ImportedStuff> getImportedMedicines() {
		return importedMedicines;
	}

}
