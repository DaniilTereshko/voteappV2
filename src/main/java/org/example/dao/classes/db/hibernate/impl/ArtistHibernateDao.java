package org.example.dao.classes.db.hibernate.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import org.example.dao.api.IArtistHibernateDao;
import org.example.dao.classes.db.ds.HibernateUtil;
import org.example.dao.classes.db.hibernate.entities.ArtistEntity;

import java.util.List;

public class ArtistHibernateDao implements IArtistHibernateDao {
    @Override
    public List<ArtistEntity> get() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ArtistEntity> query = criteriaBuilder.createQuery(ArtistEntity.class);
        Root<ArtistEntity> from = query.from(ArtistEntity.class);
        query.select(from);
        List<ArtistEntity> resultList = entityManager.createQuery(query).getResultList();
        transaction.commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public ArtistEntity get(long id) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        ArtistEntity artistEntity = entityManager.find(ArtistEntity.class, id);
        transaction.commit();
        entityManager.close();
        return artistEntity;
    }

    @Override
    public ArtistEntity save(ArtistEntity item) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(item);
        transaction.commit();
        entityManager.close();
        return item;
    }
}
