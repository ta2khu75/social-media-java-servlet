/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author ta2khu75
 */
public class Jpa {
	private static EntityManagerFactory entityManagerFactory;

	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
	}

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public static <E> boolean presist(E entity) {
		boolean result = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(entity);
			transaction.commit();
			result = true;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}e.printStackTrace();
		} finally {
			entityManager.close();
			return result;
		}

	}

	public static <E> boolean merge(E entity) {
		boolean result = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(entity);
			transaction.commit();
			result = true;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
			return result;
		}
	}

	public static <T, E> boolean remove(T t, E id) {
		boolean result = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		T entity = (T) entityManager.find(t.getClass(), id);// (T) entityManager.find(t.getClass(), id);
		if (entity == null) {
			return result;
		}
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.remove(entity);
			transaction.commit();
			result = true;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	public static <T, E> T find(T t, E id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		T entity = (T) entityManager.find(t.getClass(), id);
		return entity;
	}

	public static <E> List<E> selectAll(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String classString = entity.getClass().toString();
		String nameEntity = classString.substring(classString.lastIndexOf(".") + 1);
		// System.out.println(nameEntity);
		String jpql = "SELECT o FROM " + nameEntity + " o";
		// System.out.println(jpql);
		TypedQuery<E> query = (TypedQuery<E>) entityManager.createQuery(jpql, entity.getClass());
		List<E> list = query.getResultList();
		return list;
	}

}
