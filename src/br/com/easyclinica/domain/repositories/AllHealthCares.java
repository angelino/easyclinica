package br.com.easyclinica.domain.repositories;

import java.util.List;

import br.com.easyclinica.domain.entities.HealthCare;

public interface AllHealthCares {
	void add(HealthCare healthCare);
	List<HealthCare> get();
}
