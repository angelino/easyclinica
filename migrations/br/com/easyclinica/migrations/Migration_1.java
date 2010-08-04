package br.com.easyclinica.migrations;

import com.eroi.migrate.Migration;
import static com.eroi.migrate.Define.*;
import static com.eroi.migrate.Define.DataTypes.*;
import static com.eroi.migrate.Execute.*; 

public class Migration_1 implements Migration {

	public void down() {
		dropTable("healthcare");
	}

	public void up() {
		createTable(table("healthcare",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("name", VARCHAR, length(50), notnull())
		));
	}

}
