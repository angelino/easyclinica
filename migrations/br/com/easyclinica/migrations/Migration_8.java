package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.*;
import static com.eroi.migrate.Define.DataTypes.*;
import static com.eroi.migrate.Execute.*;

import com.eroi.migrate.Migration;

public class Migration_8 implements Migration {

	public void down() {
		dropTable("procedure");
	}

	public void up() {
		createTable(table("procedure",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("ch_qty", INTEGER, notnull()),
				column("amb_code", VARCHAR, length(20), notnull()),
				column("tuss_code", VARCHAR, length(20), notnull()),
				column("name", VARCHAR, length(100), notnull())
		));
	}
}
