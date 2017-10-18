package com.meession.market.common.support.dao;

import java.util.List;
import java.util.Map;

/**
 * @author sam
 */
public interface IDaoTemplate {

	public boolean contains(Object entity);;

	public void refresh(Object entity);

	public void save(Object entity);

	public void update(Object entity);

	public void delete(Object entity);

	public void delete(Class<?> entityClass, Object id);

	public void delete(Class<?> entityClass, List<?> ids);

	public void execute(String queryString);

	public void execute(String queryString, Object... params);

	public void execute(String queryString, Map<String, ?> params);

	public void executeBySql(String queryString);

	public void executeBySql(String queryString, Object... params);

	public void executeBySql(String queryString, Map<String, ?> params);

	public <T> T find(Class<T> entityClass, Object id);

	public <T> T find(Class<T> resultClass, String queryString);

	public <T> T find(Class<T> resultClass, String queryString,
			Object... params);

	public <T> T find(Class<T> resultClass, String queryString,
			Map<String, ?> params);

	public Object findBySql(String queryString);

	public Object findBySql(String queryString, Object... params);

	public Object findBySql(String queryString, Map<String, ?> params);

	public <T> List<T> list(Class<T> entityClass);

	public <T> List<T> list(Class<T> entityClass, List<?> ids);

	public <T> List<T> list(Class<T> resultClass, String queryString);

	public <T> List<T> list(Class<T> resultClass, String queryString,
			Object... params);

	public <T> List<T> list(Class<T> resultClass, String queryString,
			Map<String, ?> params);

	public <T> List<T> list(Class<T> entityClass, int firstResult, int maxResult);

	public <T> List<T> list(Class<T> entityClass, int firstResult,
			int maxResult, List<?> ids);

	public <T> List<T> list(Class<T> resultClass, String queryString,
			int firstResult, int maxResult);

	public <T> List<T> list(Class<T> resultClass, String queryString,
			int firstResult, int maxResult, Object... params);

	public <T> List<T> list(Class<T> resultClass, String queryString,
			int firstResult, int maxResult, Map<String, ?> params);

	@SuppressWarnings("rawtypes")
	public List listBySql(String queryString);

	@SuppressWarnings("rawtypes")
	public List listBySql(String queryString, Object... params);

	@SuppressWarnings("rawtypes")
	public List listBySql(String queryString, Map<String, ?> params);

	@SuppressWarnings("rawtypes")
	public List listBySql(String queryString, int firstResult, int maxResult);

	@SuppressWarnings("rawtypes")
	public List listBySql(String queryString, int firstResult, int maxResult,
			Object... params);

	@SuppressWarnings("rawtypes")
	public List listBySql(String queryString, int firstResult, int maxResult,
			Map<String, ?> params);

	public long count(Class<?> entityClass);

	public long count(String queryString);

	public long count(String queryString, Object... params);

	public long count(String queryString, Map<String, ?> params);

	public long countBySql(String queryString);

	public long countBySql(String queryString, Object... params);

	public long countBySql(String queryString, Map<String, ?> params);

}
