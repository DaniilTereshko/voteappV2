package org.example.services.factory;

import org.example.dao.factory.VoteDaoFactory;
import org.example.services.classes.VoteService;
import org.example.services.api.IVoteService;

public class VoteServiceFactory {
    private static IVoteService voteService = null;

    private VoteServiceFactory() {
    }

    public static IVoteService getInstance(){
        if(voteService == null){
            synchronized (GenreServiceFactory.class){
                if(voteService == null){
                    voteService = new VoteService(VoteDaoFactory.getInstance(), ArtistServiceFactory.getInstance(), GenreServiceFactory.getInstance());
                }
            }
        }
        return voteService;
    }
}
