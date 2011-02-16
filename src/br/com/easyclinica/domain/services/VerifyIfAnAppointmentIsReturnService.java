package br.com.easyclinica.domain.services;

import java.util.Calendar;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.domain.repositories.AllAppointments;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;

@Component
public class VerifyIfAnAppointmentIsReturnService {
	private final AllHealthCarePlans allHealthCarePlans;
	private final AllAppointments allAppointments;
	
	public VerifyIfAnAppointmentIsReturnService(AllHealthCarePlans allHealthCarePlans, AllAppointments allAppointments) {
		this.allHealthCarePlans = allHealthCarePlans;
		this.allAppointments = allAppointments;
	}
	
	public boolean check(int patientId, int specialtyId, int healthCarePlanId) {
		HealthCarePlan plan = allHealthCarePlans.getById(healthCarePlanId);
		Appointment appointment = allAppointments.getLastFromPatientAndSpecialty(new Patient(patientId), new Specialty(specialtyId));
		
		if(appointment == null) return false;
			
		boolean isReturn = false;
		
		Calendar lastDayForReturn = appointment.getAppointmentDate();
		lastDayForReturn.add(Calendar.DAY_OF_MONTH, plan.getPeriodToReturn());
		
		if(lastDayForReturn.after(Calendar.getInstance())) isReturn = true;
		
		return isReturn;
	}
}
