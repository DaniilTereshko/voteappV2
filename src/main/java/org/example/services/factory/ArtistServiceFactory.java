package org.example.services.factory;

import org.example.dao.factory.ArtistDaoFactory;
import org.example.services.classes.ArtistService;
import org.example.services.api.IArtistService;

public class ArtistServiceFactory {
    private static IArtistService artistService = null;

    private ArtistServiceFactory() {
    }

    public static IArtistService getInstance(){
        if(artistService == null){
            synchronized (ArtistServiceFactory.class){
                if(artistService == null){
                    artistService = new ArtistService(ArtistDaoFactory.getInstance());
                }
            }
        }
        return artistService;
    }
}
