package com.meession.market.common.support.dao;

import java.util.List;

/**
 * Generic base interface for DAOs, defining template methods for DAO
 * initialization.
 * 
 * @author sam
 */
public interface IDaoSupport {

	public IDaoTemplate getDaoTemplate();

	public void setDaoTemplate(IDaoTemplate daoTemplate);

	public boolean contains(Object entity);;

	public void save(Object entity);

	public void update(Object entity);

	public void delete(Object entity);

	public <T> void delete(Class<T> entityClass, Object id);

	public <T> void delete(Class<T> entityClass, List<?> ids);

	public <T> T find(Class<T> entityClass, Object id);

	public <T> List<T> list(Class<T> entityClass);

	public <T> List<T> list(Class<T> entityClass, List<?> ids);

	public <T> List<T> list(Class<T> entityClass, int firstResult, int maxResult);

	public <T> List<T> list(Class<T> entityClass, int firstResult,
			int maxResult, List<?> ids);

}
