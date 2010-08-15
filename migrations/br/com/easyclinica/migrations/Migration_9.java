package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.column;
import static com.eroi.migrate.Define.defaultValue;
import static com.eroi.migrate.Define.notnull;
import static com.eroi.migrate.Define.DataTypes.BOOLEAN;
import static com.eroi.migrate.Execute.addColumn;
import static com.eroi.migrate.Execute.dropColumn;

import com.eroi.migrate.Migration;

public class Migration_9 implements Migration {

	public void down() {
		dropColumn("active", "healthcareplan");
	}

	public void up() {
		addColumn(column("active", BOOLEAN, notnull(), defaultValue(1)), "healthcareplan");
	}

}
