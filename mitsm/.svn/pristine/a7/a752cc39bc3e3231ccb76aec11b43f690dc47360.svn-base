package com.meession.market.common.support.dao;

import java.util.List;

import javax.annotation.Resource;

/**
 * Generic base class for DAOs, defining template methods for DAO
 * initialization.
 *
 * @author sam
 */
public abstract class DaoSupport implements IDaoSupport {

	@Resource
	private IDaoTemplate daoTemplate;
	
	
	@Override
	public IDaoTemplate getDaoTemplate() {
		return daoTemplate;
	}

	@Override
	public void setDaoTemplate(IDaoTemplate daoTemplate) {
		this.daoTemplate = daoTemplate;
	}

	@Override
	public boolean contains(Object entity) {
		return getDaoTemplate().contains(entity);
	}

	@Override
	public void save(Object entity) {
		getDaoTemplate().save(entity);
	}

	@Override
	public void update(Object entity) {
		getDaoTemplate().update(entity);
	}

	@Override
	public void delete(Object entity) {
		getDaoTemplate().delete(entity);
	}

	@Override
	public <T> void delete(Class<T> entityClass, Object id) {
		getDaoTemplate().delete(entityClass, id);
	}

	@Override
	public <T> void delete(Class<T> entityClass, List<?> ids) {
		getDaoTemplate().delete(entityClass, ids);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object id) {
		return getDaoTemplate().find(entityClass, id);
	}

	@Override
	public <T> List<T> list(Class<T> entityClass) {
		return getDaoTemplate().list(entityClass);
	}

	@Override
	public <T> List<T> list(Class<T> entityClass, List<?> ids) {
		return getDaoTemplate().list(entityClass, ids);
	}

	@Override
	public <T> List<T> list(Class<T> entityClass, int firstResult, int maxResult) {
		return getDaoTemplate().list(entityClass, firstResult, maxResult);
	}

	@Override
	public <T> List<T> list(Class<T> entityClass, int firstResult,
			int maxResult, List<?> ids) {
		return getDaoTemplate().list(entityClass, firstResult, maxResult, ids);
	}

}
