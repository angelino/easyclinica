package br.com.easyclinica.database;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Ignore;
import org.junit.Test;

import br.com.easyclinica.infra.database.DatabaseInfo;


public class ExportTables {

	@Test @Ignore
	public void exportDatabase() {
		Configuration cfg = DatabaseInfo.config("easyclinicatest");
		
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}
}
