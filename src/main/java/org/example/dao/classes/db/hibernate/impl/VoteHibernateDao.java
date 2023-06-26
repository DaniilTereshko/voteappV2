package org.example.dao.classes.db.hibernate.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.dao.api.IVoteHibernateDao;
import org.example.dao.classes.db.ds.HibernateUtil;
import org.example.dao.classes.db.hibernate.entities.GenreEntity;
import org.example.dao.classes.db.hibernate.entities.VoteEntity;

import java.util.List;

public class VoteHibernateDao implements IVoteHibernateDao {
    private EntityManagerFactory emf;

    public VoteHibernateDao() {
    }

    public VoteHibernateDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    @Override
    public List<VoteEntity> get() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VoteEntity> query = criteriaBuilder.createQuery(VoteEntity.class);
        Root<VoteEntity> from = query.from(VoteEntity.class);
        query.select(from);
        List<VoteEntity> resultList = entityManager.createQuery(query).getResultList();
        transaction.commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public VoteEntity get(long id) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        VoteEntity voteEntity = entityManager.find(VoteEntity.class, id);
        transaction.commit();
        entityManager.close();
        return voteEntity;
    }

    @Override
    public VoteEntity save(VoteEntity item) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(item);//TODO разобраться с merge
        transaction.commit();
        entityManager.close();
        return item;
    }
}
