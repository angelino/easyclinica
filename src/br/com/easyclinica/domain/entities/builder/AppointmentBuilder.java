package br.com.easyclinica.domain.entities.builder;

import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.AppointmentProcedure;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.domain.repositories.AllProcedures;
import br.com.easyclinica.domain.repositories.AllSpecialties;
import br.com.easyclinica.view.model.AppointmentData;
import br.com.easyclinica.view.model.ProcedureData;

public class AppointmentBuilder {

	private final AllPatients allPatients;
	private final AllHealthCarePlans allHealthCarePlans;
	private final AllDoctors allDoctors;
	private final AllSpecialties allSpecialties;
	private Appointment a;
	private final AllProcedures allProcedures;

	public AppointmentBuilder(AllPatients allPatients, AllHealthCarePlans allHealthCarePlans, AllDoctors allDoctors, AllSpecialties allSpecialties, AllProcedures allProcedures) {
		this.allPatients = allPatients;
		this.allHealthCarePlans = allHealthCarePlans;
		this.allDoctors = allDoctors;
		this.allSpecialties = allSpecialties;
		this.allProcedures = allProcedures;
	}
	
	public Appointment basedOn(AppointmentData data) {
		a = new Appointment();
		
		getBasicInfo(data);
		
		for(ProcedureData procedureData : data.getProcedures()) {
			Procedure procedure = allProcedures.getById(procedureData.getId());
			
			AppointmentProcedure appointmentProcedure = new AppointmentProcedure();
			appointmentProcedure.setProcedure(procedure);
			
			a.addProcedure(appointmentProcedure);
			
		}
		
		return a;
	}

	private void getBasicInfo(AppointmentData data) {
		a.setPatient(allPatients.getById(data.getPatient()));
		a.setHealthCarePlan(allHealthCarePlans.getById(data.getHealthCarePlan()));
		a.setDoctor(allDoctors.getById(data.getDoctor()));
		a.setSpecialty(allSpecialties.getById(data.getSpecialty()));
		a.setDate(data.getDate());
		a.setReturn(data.isReturn());
	}

}
