package org.example.dao.factory;

import org.example.dao.api.IArtistDao;
import org.example.dao.api.IArtistHibernateDao;
import org.example.dao.classes.db.hibernate.impl.ArtistHibernateDao;
import org.example.dao.classes.db.jdbc.ArtistJdbcDao;

public class ArtistDaoFactory {
    private static IArtistHibernateDao artistDao = null;

    private ArtistDaoFactory() {
    }

    public static IArtistHibernateDao getInstance(){
        if(artistDao == null){
            synchronized (ArtistDaoFactory.class){
                if(artistDao == null){
                    artistDao = new ArtistHibernateDao();
                }
            }
        }
        return artistDao;
    }
}
