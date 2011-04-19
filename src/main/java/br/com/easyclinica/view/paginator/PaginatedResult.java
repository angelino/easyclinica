package br.com.easyclinica.view.paginator;

import java.util.List;

public class PaginatedResult<T> {

	private final List<T> result;
	private final int totalPages;
	private final int currentPage;

	public PaginatedResult(List<T> result, int currentPage, int totalPages) {
		this.result = result;
		this.currentPage = currentPage;
		this.totalPages = totalPages;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<T> getResult() {
		return result;
	}

	public int getCurrentPage() {
		return currentPage;
	}

}
