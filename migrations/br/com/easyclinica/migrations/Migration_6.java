package br.com.easyclinica.migrations;

import com.eroi.migrate.Migration;
import static com.eroi.migrate.Define.*;
import static com.eroi.migrate.Define.DataTypes.*;
import static com.eroi.migrate.Execute.*;

public class Migration_6 implements Migration {

	public void down() {
		dropTable("specialty");

	}

	public void up() {
		createTable(table("specialty",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("name", VARCHAR, length(100), notnull())
		));

	}

}
