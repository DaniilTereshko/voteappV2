package org.example.services.factory;

import org.example.dao.factory.GenreDaoFactory;
import org.example.services.classes.GenreService;
import org.example.services.api.IGenreService;

public class GenreServiceFactory {
    private static IGenreService genreService = null;

    private GenreServiceFactory() {
    }

    public static IGenreService getInstance(){
        if(genreService == null){
            synchronized (GenreServiceFactory.class){
                if(genreService == null){
                    genreService = new GenreService(GenreDaoFactory.getInstance());
                }
            }
        }
        return genreService;
    }
}
