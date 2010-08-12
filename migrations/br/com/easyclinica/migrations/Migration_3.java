package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.autoincrement;
import static com.eroi.migrate.Define.column;
import static com.eroi.migrate.Define.length;
import static com.eroi.migrate.Define.notnull;
import static com.eroi.migrate.Define.primarykey;
import static com.eroi.migrate.Define.table;
import static com.eroi.migrate.Define.DataTypes.INTEGER;
import static com.eroi.migrate.Define.DataTypes.VARCHAR;
import static com.eroi.migrate.Execute.createTable;
import static com.eroi.migrate.Execute.dropTable;

import com.eroi.migrate.Migration;

public class Migration_3 implements Migration {

	public void down() {
		dropTable("servicestable");
	}

	public void up() {
		createTable(table("servicestable",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("name", VARCHAR, length(50), notnull()))
		);
	}

}
