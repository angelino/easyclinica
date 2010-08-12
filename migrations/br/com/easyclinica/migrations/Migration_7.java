package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.column;
import static com.eroi.migrate.Define.notnull;
import static com.eroi.migrate.Define.primarykey;
import static com.eroi.migrate.Define.table;
import static com.eroi.migrate.Define.DataTypes.INTEGER;
import static com.eroi.migrate.Execute.createTable;
import static com.eroi.migrate.Execute.dropTable;

import com.eroi.migrate.Migration;

public class Migration_7 implements Migration {

	public void down() {
		dropTable("service_materials");
	}

	public void up() {
		createTable(table("service_materials",
				column("service_fk", INTEGER, primarykey(), notnull()),
				column("material_fk", INTEGER, primarykey(), notnull()))
		);
	}

}
