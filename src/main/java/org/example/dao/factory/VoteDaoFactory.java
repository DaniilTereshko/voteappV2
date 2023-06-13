package org.example.dao.factory;

import org.example.dao.api.IVoteDao;
import org.example.dao.api.IVoteHibernateDao;
import org.example.dao.classes.db.hibernate.impl.VoteHibernateDao;
import org.example.dao.classes.db.jdbc.VoteJdbcDao;

public class VoteDaoFactory {
    private static IVoteHibernateDao voteDao = null;

    private VoteDaoFactory() {
    }

    public static IVoteHibernateDao getInstance(){
        if(voteDao == null){
            synchronized (GenreDaoFactory.class){
                if(voteDao == null){
                    voteDao = new VoteHibernateDao();
                }
            }
        }
        return voteDao;
    }
}
