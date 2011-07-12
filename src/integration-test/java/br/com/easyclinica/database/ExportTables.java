package br.com.easyclinica.database;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.junit.Ignore;
import org.junit.Test;

import br.com.easyclinica.config.Config;
import br.com.easyclinica.config.HardCodedConfigForTests;
import br.com.easyclinica.infra.database.DatabaseConfigurator;

public class ExportTables {

	@Test @Ignore
	public void createNewSchema() {
		Configuration cfg = new DatabaseConfigurator(new HardCodedConfigForTests()).config("easyclinicatest");
		
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}
	
	@Test @Ignore
	public void updateSchema() {
		Configuration cfg = new DatabaseConfigurator(new HardCodedConfigForTests()).config("easyclinica");
		
		SchemaUpdate se = new SchemaUpdate(cfg);
		se.execute(true, true);
		
	}
	
	@Test @Ignore
	public void createHomologSchemaInCloudbess() {
		final String server = "ec2-75-101-156-134.compute-1.amazonaws.com";
		final String customer = "teste";
		
		Configuration cfg = new DatabaseConfigurator(new Config() {
			public String get(String key) {
				if(key.equals("connection_string")) return "jdbc:mysql://"+server+"/#database#?useUnicode=true&characterEncoding=UTF-8";
				if(key.equals("driver_class")) return "com.mysql.jdbc.Driver";
				if(key.equals("db_pwd")) return "3@sycl1n1c@";
				if(key.equals("db_prefix")) return "ec-";
				if(key.equals("dialect")) return "org.hibernate.dialect.MySQLInnoDBDialect";
				
				return "";
			}
		}).config(customer);
		
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
		
		// export specialties
		
		// export medicines
		
	}
}
