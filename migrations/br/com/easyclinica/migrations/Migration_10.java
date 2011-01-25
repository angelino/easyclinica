package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.*;
import static com.eroi.migrate.Execute.*;
import static com.eroi.migrate.Define.DataTypes.*;

import com.eroi.migrate.Migration;

public class Migration_10 implements Migration{


	public void down() {
		dropTable("medicine");
	}

	public void up() {
		createTable(table("medicine",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("name", VARCHAR, length(100), notnull())
		));
	}
}
