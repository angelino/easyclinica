package br.com.easyclinica.migrations;

import com.eroi.migrate.Migration;
import static com.eroi.migrate.Define.*;
import static com.eroi.migrate.Define.DataTypes.*;
import static com.eroi.migrate.Execute.*; 

public class Migration_5 implements Migration {

	public void down() {
		dropTable("appointment");

	}

	public void up() {
		createTable(table("appointment", 
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("clinic_fk", INTEGER, notnull()),
				column("healthcareplan_fk", INTEGER, notnull()),
				column("doctor_fk", INTEGER, notnull()),
				column("patient_fk", INTEGER, notnull()),
				column("is_return", BOOLEAN, notnull(), defaultValue(0)),
				column("appointment_date", DATE, notnull()),
				column("date", DATE, notnull()),
				column("appointment_amount", DOUBLE, notnull(), defaultValue(0)),
				column("procedure_amount", DOUBLE, notnull(), defaultValue(0)),
				column("specialty_fk", INTEGER, notnull()),
				column("observations", LONGVARCHAR)
		));
	}

}
