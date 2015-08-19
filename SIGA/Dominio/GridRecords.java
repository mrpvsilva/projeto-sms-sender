package Dominio;

import java.util.List;

public class GridRecords<E> {

	private List<E> lista;
	private int pageIndex;
	private int recordsCount;
	private long totalRecords;

	public List<E> getLista() {
		return lista;
	}

	public void setLista(List<E> lista) {
		this.lista = lista;
	}

	public int getPageSize() {

		int t = (int) Math.ceil((double) totalRecords / (double) recordsCount);

		return t;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
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
