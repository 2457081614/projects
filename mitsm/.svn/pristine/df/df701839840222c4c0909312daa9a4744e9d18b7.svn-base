package com.meession.market.common.support.dao.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.meession.market.common.support.dao.IDaoTemplate;

/**
 * @author sam
 */
public class JpaDaoTemplate implements IDaoTemplate {

	@PersistenceContext
	private EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private void prepareQuery(Query query, Object... params) {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
	}

	private void prepareQuery(Query query, Map<String, ?> params) {
		if (params != null) {
			for (Map.Entry<String, ?> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
	}

	private void prepareQuery(Query query, int firstResult, int maxResult) {
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
	}

	private void prepareQuery(Query query, int firstResult, int maxResult,
			Object... params) {
		prepareQuery(query, params);
		prepareQuery(query, firstResult, maxResult);
	}

	private void prepareQuery(Query query, int firstResult, int maxResult,
			Map<String, ?> params) {
		prepareQuery(query, params);
		prepareQuery(query, firstResult, maxResult);
	}

	@Override
	public boolean contains(Object entity) {
		return em.contains(entity);
	}

	@Override
	public void refresh(Object entity) {
		em.refresh(entity);
	}

	@Override
	public void save(Object entity) {
		em.persist(entity);
	}

	@Override
	public void update(Object entity) {
		em.merge(entity);
	}

	@Override
	@Transactional
	public void delete(Object entity) {
		em.remove(entity);
	}

	@Override
	public void delete(Class<?> entityClass, Object id) {
		em.remove(em.getReference(entityClass, id));
	}

	@Override
	public void delete(Class<?> entityClass, List<?> ids) {
		String queryString = "DELETE FROM " + entityClass.getName()
				+ " entity WHERE entity.id IN (:ids)";
		Query query = em.createQuery(queryString);
		query.setParameter("ids", ids);
		query.executeUpdate();
	}

	@Override
	public void execute(String queryString) {
		Query query = em.createQuery(queryString);
		query.executeUpdate();
	}

	@Override
	public void execute(String queryString, Object... params) {
		Query query = em.createQuery(queryString);
		prepareQuery(query, params);
		query.executeUpdate();
	}

	@Override
	public void execute(String queryString, Map<String, ?> params) {
		Query query = em.createQuery(queryString);
		prepareQuery(query, params);
		query.executeUpdate();
	}

	@Override
	public void executeBySql(String queryString) {
		Query query = em.createNativeQuery(queryString);
		query.executeUpdate();
	}

	@Override
	public void executeBySql(String queryString, Object... params) {
		Query query = em.createNativeQuery(queryString);
		prepareQuery(query, params);
		query.executeUpdate();
	}

	@Override
	public void executeBySql(String queryString, Map<String, ?> params) {
		Query query = em.createNativeQuery(queryString);
		prepareQuery(query, params);
		query.executeUpdate();
	}

	@Override
	public <T> T find(Class<T> entityClass, Object id) {
		return em.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T find(Class<T> resultClass, String queryString) {
		Query query = em.createQuery(queryString);
		prepareQuery(query, 0, 1);
		List<T> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T find(Class<T> resultClass, String queryString,
			Object... params) {
		Query query = em.createQuery(queryString);
		prepareQuery(query, 0, 1, params);
		List<T> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T find(Class<T> resultClass, String queryString,
			Map<String, ?> params) {
		Query query = em.createQuery(queryString);
		prepareQuery(query, 0, 1, params);
		List<T> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Object findBySql(String queryString) {
		Query query = em.createNativeQuery(queryString);
		prepareQuery(query, 0, 1);
		List<?> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Object findBySql(String queryString, Object... params) {
		Query query = em.createNativeQuery(queryString);
		prepareQuery(query, 0, 1, params);
		List<?> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Object findBySql(String queryString, Map<String, ?> params) {
		Query query = em.createNativeQuery(queryString);
		prepareQuery(query, 0, 1, params);
		List<?> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public <T> List<T> list(Class<T> entityClass) {
		String queryString = "SELECT entity FROM " + entityClass.getName()
				+ " entity";
		return list(entityClass, queryString);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> entityClass, List<?> ids) {
		String queryString = "SELECT entity FROM " + entityClass.getName()
				+ " entity WHERE entity.id IN (:ids)";
		Query query = em.createQuery(queryString);
		query.setParameter("ids", ids);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> resultClass, String queryString) {
		Query query = em.createQuery(queryString);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> resultClass, String queryString,
			Object... params) {
		Query query = em.createQuery(queryString);
		prepareQuery(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> resultClass, String queryString,
			Map<String, ?> params) {
		Query query = em.createQuery(queryString);
		prepareQuery(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> entityClass, int firstResult, int maxResult) {
		String queryString = "SELECT entity FROM " + entityClass.getName()
				+ " entity";
		Query query = em.createQuery(queryString);
		prepareQuery(query, firstResult, maxResult);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> entityClass, int firstResult,
			int maxResult, List<?> ids) {
		String queryString = "SELECT entity FROM " + entityClass.getName()
				+ " entity WHERE entity.id IN (:ids)";
		Query query = em.createQuery(queryString);
		prepareQuery(query, firstResult, maxResult);
		query.setParameter("ids", ids);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> resultClass, String queryString,
			int firstResult, int maxResult) {
		Query query = em.createQuery(queryString);
		prepareQuery(query, firstResult, maxResult);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> resultClass, String queryString,
			int firstResult, int maxResult, Object... params) {
		Query query = em.createQuery(queryString);
		prepareQuery(query, firstResult, maxResult, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> resultClass, String queryString,
			int firstResult, int maxResult, Map<String, ?> params) {
		Query query = em.createQuery(queryString);
		prepareQuery(query, firstResult, maxResult, params);
		return query.getResultList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List listBySql(String queryString) {
		Query query = em.createNativeQuery(queryString);
		return query.getResultList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List listBySql(String queryString, Object... params) {
		Query query = em.createNativeQuery(queryString);
		prepareQuery(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List listBySql(String queryString, Map<String, ?> params) {
		Query query = em.createNativeQuery(queryString);
		prepareQuery(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List listBySql(String queryString, int firstResult, int maxResult) {
		Query query = em.createNativeQuery(queryString);
		prepareQuery(query, firstResult, maxResult);
		return query.getResultList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List listBySql(String queryString, int firstResult, int maxResult,
			Object... params) {
		Query query = em.createNativeQuery(queryString);
		prepareQuery(query, firstResult, maxResult, params);
		return query.getResultList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List listBySql(String queryString, int firstResult, int maxResult,
			Map<String, ?> params) {
		Query query = em.createNativeQuery(queryString);
		prepareQuery(query, firstResult, maxResult, params);
		return query.getResultList();
	}

	@Override
	public long count(Class<?> entityClass) {
		String queryString = "SELECT COUNT(entity) FROM "
				+ entityClass.getName() + " entity";
		Query query = em.createQuery(queryString);
		return (Long) query.getSingleResult();
	}

	@Override
	public long count(String queryString) {
		Query query = em.createQuery(queryString);
		return (Long) query.getSingleResult();
	}

	@Override
	public long count(String queryString, Object... params) {
		Query query = em.createQuery(queryString);
		prepareQuery(query, params);
		return (Long) query.getSingleResult();
	}

	@Override
	public long count(String queryString, Map<String, ?> params) {
		Query query = em.createQuery(queryString);
		prepareQuery(query, params);
		return (Long) query.getSingleResult();
	}

	@Override
	public long countBySql(String queryString) {
		Query query = em.createNativeQuery(queryString);
		return Long.parseLong(query.getSingleResult() + "");
	}

	@Override
	public long countBySql(String queryString, Object... params) {
		Query query = em.createNativeQuery(queryString);
		prepareQuery(query, params);
		return Long.parseLong(query.getSingleResult() + "");
	}

	@Override
	public long countBySql(String queryString, Map<String, ?> params) {
		Query query = em.createNativeQuery(queryString);
		prepareQuery(query, params);
		return Long.parseLong(query.getSingleResult() + "");
	}
}
