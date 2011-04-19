package br.com.easyclinica.domain.repositories;

import br.com.easyclinica.domain.entities.Receipt;

public interface AllReceipts {
	void add(Receipt receipt);
	void update(Receipt receipt);
	void delete(Receipt receipt);
	
	Receipt getById(int id);
}
