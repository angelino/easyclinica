package br.com.easyclinica.view.paginator;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.config.Config;
import br.com.easyclinica.domain.repositories.Pagging;

@Component
public class Paginator {

	private final int elementsPerPage;
	
	public Paginator(Config config) {
		elementsPerPage = Integer.parseInt(config.get("elements_per_page"));
	}
	
	public <T> PaginatedResult<T> paginate(Pagging<T> repository, int currentPage) {
		int totalElements = repository.count();
		int totalPages = totalElements / elementsPerPage + (totalElements % elementsPerPage > 0 ? 1 : 0);
		
		int firstResult = (currentPage-1) * elementsPerPage;
		
		return new PaginatedResult<T>(repository.get(firstResult, elementsPerPage), currentPage, totalPages);
	}
	
	public <T> PaginatedResult<T> searchAndPaginate(Pagging<T> repository, String textToSearch, int currentPage) {
		int totalElements = repository.count(textToSearch);
		int totalPages = totalElements / elementsPerPage + (totalElements % elementsPerPage > 0 ? 1 : 0);
		
		int firstResult = (currentPage-1) * elementsPerPage;
		
		return new PaginatedResult<T>(repository.search(textToSearch, firstResult, elementsPerPage), currentPage, totalPages);
	}
	
	public static int firstPage() {
		return 1;
	}
}
