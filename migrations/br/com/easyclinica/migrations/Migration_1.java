package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.autoincrement;
import static com.eroi.migrate.Define.column;
import static com.eroi.migrate.Define.defaultValue;
import static com.eroi.migrate.Define.length;
import static com.eroi.migrate.Define.notnull;
import static com.eroi.migrate.Define.primarykey;
import static com.eroi.migrate.Define.table;
import static com.eroi.migrate.Define.DataTypes.BOOLEAN;
import static com.eroi.migrate.Define.DataTypes.INTEGER;
import static com.eroi.migrate.Define.DataTypes.VARCHAR;
import static com.eroi.migrate.Execute.createTable;
import static com.eroi.migrate.Execute.dropTable;

import com.eroi.migrate.Migration;

public class Migration_1 implements Migration {

	public void down() {
		dropTable("clinic");		
	}

	public void up() {
		createTable(table("clinic",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("name", VARCHAR, length(100), notnull()),
				column("domain", VARCHAR, length(100), notnull()),
				column("active", BOOLEAN, notnull(), defaultValue(1))
		));
		
	}

}
