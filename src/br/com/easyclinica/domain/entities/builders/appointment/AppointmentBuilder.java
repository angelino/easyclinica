package br.com.easyclinica.domain.entities.builders.appointment;

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
	private Appointment appointment;
	private final AllProcedures allProcedures;
	private AppointmentData data;

	public AppointmentBuilder(AllPatients allPatients, AllHealthCarePlans allHealthCarePlans, AllDoctors allDoctors, AllSpecialties allSpecialties, AllProcedures allProcedures) {
		this.allPatients = allPatients;
		this.allHealthCarePlans = allHealthCarePlans;
		this.allDoctors = allDoctors;
		this.allSpecialties = allSpecialties;
		this.allProcedures = allProcedures;
	}
	
	public AppointmentBuilder basedOn(AppointmentData data) {
		this.data = data;
		appointment = new Appointment();
		
		return this;
	}
	
	public Appointment build() {
		return appointment;
	}

	public AppointmentBuilder withProcedures() {
		for(ProcedureData procedureData : data.getProcedures()) {
			Procedure procedure = allProcedures.getById(procedureData.getId());
			
			AppointmentProcedure appointmentProcedure = new AppointmentProcedure();
			appointmentProcedure.setProcedure(procedure);
			
			appointment.addProcedure(appointmentProcedure);
			
		}
		
		return this;
	}
	
	public AppointmentBuilder withBasicInfo() {
		appointment.setPatient(allPatients.getById(data.getPatient()));
		appointment.setHealthCarePlan(allHealthCarePlans.getById(data.getHealthCarePlan()));
		appointment.setDoctor(allDoctors.getById(data.getDoctor()));
		appointment.setSpecialty(allSpecialties.getById(data.getSpecialty()));
		appointment.setDate(data.getDate());
		appointment.setReturn(data.isReturn());
		
		return this;
	}

}
