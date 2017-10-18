package com.meession.market.common;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

 
/**
 * @author sam
 */
public class TableDataModel<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;

	private IPagerService<T> pagerService;

	public TableDataModel(IPagerService<T> pagerService) {
		this.pagerService = pagerService;
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		Pager<T> pager = new Pager<T>();
		pager.setPageSize(pageSize);
		pager.setPageNum(first / pageSize+1);

		String sort = null;
		if (SortOrder.ASCENDING.equals(sortOrder)) {
			sort = "asc";
		} else if (SortOrder.DESCENDING.equals(sortOrder)) {
			sort = "desc";
		}
		pager.setSort(sort);
		pager.setOrderBy(sortField);

		String filterValue = null;
		if (filters != null && !filters.isEmpty()) {
			filterValue = (String) filters.get(filters.keySet().iterator()
					.next());
		}

		pager = pagerService.search(pager, filterValue);
		this.setRowCount((int) pager.getTotalNum());
		return pager.getDatas();
	}

}
