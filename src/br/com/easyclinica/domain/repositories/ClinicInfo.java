package br.com.easyclinica.domain.repositories;

import br.com.easyclinica.domain.entities.Clinic;

public interface ClinicInfo {

	Clinic get();

	void update(Clinic clinic);
}
