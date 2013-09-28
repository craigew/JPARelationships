package com.craigew.service;

import com.craigew.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.util.List;

public class DataAccess<T extends BaseEntity> {
    private Class<T> entityClass;

    protected long add(T object) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAIntroduction");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        long id = object.getId();
        em.close();
        factory.close();
        return id;
    }

    protected void update(T object) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAIntroduction");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(object);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    protected void delete(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAIntroduction");
        EntityManager em = factory.createEntityManager();
        T object = em.find(getEntityClass(), id);
        if (object != null) {
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
        }
        em.close();
        factory.close();
    }

    protected void delete(T object) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAIntroduction");
        EntityManager em = factory.createEntityManager();
        if (object != null) {
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
        }
        em.close();
        factory.close();
    }

    protected T findByPrimaryKey(int id) throws EntityNotFoundException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAIntroduction");
        EntityManager em = factory.createEntityManager();
        T returnObject = em.find(getEntityClass(), id);
        em.close();
        factory.close();
        if (returnObject == null) {
            throw new EntityNotFoundException("Entity " + getEntityClass().getName() + " with id " + id + " not found");
        }
        return returnObject;
    }

    protected List<T> queryString(String sql){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAIntroduction");
        EntityManager em = factory.createEntityManager();
        List<T> results= em.createQuery(sql).getResultList();
        em.close();
        factory.close();
        return results;
    }

    protected Class<T> getEntityClass() {
        return entityClass;
    }

    protected void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}
