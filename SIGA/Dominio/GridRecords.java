package Dominio;

import java.util.List;

public class GridRecords<E> {

	private List<E> lista;
	private int pageIndex;
	private int recordsCount;

	public GridRecords() {
		pageIndex = 1;
		recordsCount = 10;
	}

	public List<E> getLista() {
		return lista;
	}

	public void setLista(List<E> lista) {
		this.lista = lista;
	}

	public int totalRecords() {
		return lista.size();
	}

	public int getPageSize() {

		// (int)Math.Ceiling((float)totalRecords / (float)request.RecordsCount)
		return (int) Math.ceil((double) totalRecords() / (double) recordsCount);
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getRecordsCount() {
		return recordsCount;
	}

	public void setRecordsCount(int recordsCount) {
		this.recordsCount = recordsCount;
	}

}
