package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.Doctor;

public interface AllDoctors {
	List<Doctor> get();
}
