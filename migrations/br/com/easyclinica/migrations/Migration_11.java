package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.autoincrement;
import static com.eroi.migrate.Define.column;
import static com.eroi.migrate.Define.defaultValue;
import static com.eroi.migrate.Define.notnull;
import static com.eroi.migrate.Define.primarykey;
import static com.eroi.migrate.Define.table;
import static com.eroi.migrate.Define.DataTypes.DOUBLE;
import static com.eroi.migrate.Define.DataTypes.INTEGER;
import static com.eroi.migrate.Execute.createTable;
import static com.eroi.migrate.Execute.dropTable;

import com.eroi.migrate.Migration;

public class Migration_11 implements Migration {
	public void down() {
		dropTable("materialrule");
	}

	public void up() {
		createTable(table("materialrule",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("material_fk", INTEGER, notnull()),
				column("healthcareplan_fk", INTEGER, notnull()),
				column("ch", DOUBLE, notnull(), defaultValue(0)),
				column("value", DOUBLE, notnull(), defaultValue(0))
		));
	}
}
