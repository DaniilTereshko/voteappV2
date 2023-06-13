package org.example.services.factory;

import org.example.services.classes.VoteStatisticService;
import org.example.services.api.IVoteStatisticService;

public class VoteStatisticServiceFactory {
    private static IVoteStatisticService voteStatisticService = null;

    private VoteStatisticServiceFactory() {
    }

    public static IVoteStatisticService getInstance(){
        if(voteStatisticService == null){
            synchronized (GenreServiceFactory.class){
                if(voteStatisticService == null){
                    voteStatisticService = new VoteStatisticService(VoteServiceFactory.getInstance(), GenreServiceFactory.getInstance(), ArtistServiceFactory.getInstance());
                }
            }
        }
        return voteStatisticService;
    }
}
