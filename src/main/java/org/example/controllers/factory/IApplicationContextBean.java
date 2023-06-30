package org.example.controllers.factory;

import org.example.services.api.IArtistService;
import org.example.services.api.IGenreService;
import org.example.services.api.IVoteService;
import org.example.services.api.IVoteStatisticService;

public interface IApplicationContextBean {
    IArtistService getArtistService();
    IGenreService getGenreService();
    IVoteService getVoteService();
    IVoteStatisticService getVoteStatisticService();
}
