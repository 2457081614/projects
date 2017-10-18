package com.meession.market.common.support.service;

import com.meession.market.common.support.Pager;

/**
 * @author sam
 */
public interface IPagerService<T> {

	public Pager<T> search(Pager<T> pager, String filterValue);

}
