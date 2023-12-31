package org.example.controllers.factory;

import org.example.services.api.IArtistService;
import org.example.services.api.IGenreService;
import org.example.services.api.IVoteService;
import org.example.services.api.IVoteStatisticService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextBean implements IApplicationContextBean{
    private ApplicationContext context;

    public ApplicationContextBean() {
        this.context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Override
    public IArtistService getArtistService() {
        return context.getBean(IArtistService.class);
    }

    @Override
    public IGenreService getGenreService() {
        return context.getBean(IGenreService.class);
    }

    @Override
    public IVoteService getVoteService() {
        return context.getBean(IVoteService.class);
    }

    @Override
    public IVoteStatisticService getVoteStatisticService() {
        return context.getBean(IVoteStatisticService.class);
    }
}
