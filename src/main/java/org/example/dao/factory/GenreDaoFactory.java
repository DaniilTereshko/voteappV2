package org.example.dao.factory;

import org.example.dao.api.IGenreDao;
import org.example.dao.api.IGenreHibernateDao;
import org.example.dao.classes.db.hibernate.impl.GenreHibernateDao;
import org.example.dao.classes.db.jdbc.GenreJdbcDao;

public class GenreDaoFactory {
    private static IGenreHibernateDao genreDao = null;

    private GenreDaoFactory() {
    }

    public static IGenreHibernateDao getInstance(){
        if(genreDao == null){
            synchronized (GenreDaoFactory.class){
                if(genreDao == null){
                    genreDao = new GenreHibernateDao();
                }
            }
        }
        return genreDao;
    }
}
