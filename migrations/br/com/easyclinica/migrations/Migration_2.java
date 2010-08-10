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

public class Migration_2 implements Migration {
	public void down() {
		dropTable("doctors");
	}

	public void up() {
		createTable(table("doctors",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("name", VARCHAR, length(50), notnull()),
				column("crm", VARCHAR, length(50), notnull()),
				column("specialty", VARCHAR, length(150)),
				column("phone", VARCHAR, length(50)),
				column("email", VARCHAR, length(100)),
				column("observations", com.eroi.migrate.Define.DataTypes.LONGVARCHAR)
		));
	}
}
