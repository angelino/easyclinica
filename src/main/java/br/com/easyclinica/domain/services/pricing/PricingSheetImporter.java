package br.com.easyclinica.domain.services.pricing;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import br.com.caelum.vraptor.ioc.Component;
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

	public void excel(InputStream is) {
		try {
			wb = new HSSFWorkbook(is);

			extractSpecialties();
			extractMaterials();
			extractMedicines();
			extractProcedures();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private void extractSpecialties() {
		if (wb.getSheet("Especialidades") != null) {
			extract(wb.getSheet("Especialidades"), importedSpecialties);
		}
	}

	private void extractMaterials() {
		if (wb.getSheet("Materiais") != null) {
			extract(wb.getSheet("Materiais"), importedMaterials);
		}
	}

	private void extractMedicines() {
		if (wb.getSheet("Remédios") != null) {
			extract(wb.getSheet("Remédios"), importedMedicines);
		}
	}

	private void extractProcedures() {
		if (wb.getSheet("Procedimentos") != null) {
			extractProcedure(wb.getSheet("Procedimentos"), importedProcedures);
		}
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
