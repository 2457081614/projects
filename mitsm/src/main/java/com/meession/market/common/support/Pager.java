package com.meession.market.common.support;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author sam
 */
public class Pager<T> {

	// @Min(1)
	private int pageSize;

	// @Min(0)
	private int pageNum;

	private String orderBy;

	private String sort;

	private long totalNum;

	private List<T> datas;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	/**
	 * get the first result number in current page, from 0.
	 */
	public int getFirstNum() {
		return pageSize * pageNum;
	}

	/**
	 * Generate sql clause of order by
	 */
	public String getOrderString() {
		String orderString = "";
		if (StringUtils.isNotBlank(this.orderBy)) {
			orderString += " ORDER BY " + this.orderBy;
			if ("asc".equals(this.sort)) {
				orderString += " asc";
			} else if ("desc".equals(this.sort)) {
				orderString += " desc";
			}
		}
		return orderString;
	}

}
