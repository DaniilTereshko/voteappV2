package org.example.dao.classes.db.hibernate.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.dao.api.IGenreHibernateDao;
import org.example.dao.classes.db.hibernate.entities.GenreEntity;

import java.util.List;

public class GenreHibernateDao implements IGenreHibernateDao {
    private EntityManagerFactory emf;

    public GenreHibernateDao() {
    }

    public GenreHibernateDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    @Override
    public List<GenreEntity> get() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GenreEntity> query = criteriaBuilder.createQuery(GenreEntity.class);
        Root<GenreEntity> from = query.from(GenreEntity.class);
        query.select(from);
        List<GenreEntity> resultList = entityManager.createQuery(query).getResultList();
        transaction.commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public GenreEntity get(long id) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        GenreEntity genreEntity = entityManager.find(GenreEntity.class, id);
        transaction.commit();
        entityManager.close();
        return genreEntity;
    }

    @Override
    public GenreEntity save(GenreEntity item) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(item);
        transaction.commit();
        entityManager.close();
        return item;
    }
}
