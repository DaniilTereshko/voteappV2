package org.example.controllers.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.core.dto.VoteCreatorDTO;
import org.example.services.api.IArtistService;
import org.example.services.api.IGenreService;
import org.example.services.api.IVoteService;
import org.example.controllers.factory.ApplicationContextFactory;


import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private static final String ARTIST_PARAM_NAME = "artist";
    private static final String GENRE_PARAM_NAME = "genre";
    private static final String ABOUT_PARAM_NAME = "about";
    private IArtistService artistService;
    private IGenreService genreService;
    private IVoteService voteService;

    public VoteServlet() {
        this.artistService = ApplicationContextFactory.getInstance().getBean(IArtistService.class);;
        this.genreService = ApplicationContextFactory.getInstance().getBean(IGenreService.class);
        this.voteService = ApplicationContextFactory.getInstance().getBean(IVoteService.class);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("artists", artistService.get());
        req.setAttribute("genres", genreService.get());
        req.getRequestDispatcher("/WEB-INF/vote.jspx").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] artistRaw = parameterMap.get(ARTIST_PARAM_NAME);
        if(artistRaw == null || artistRaw.length != 1){
            throw new IllegalArgumentException("Артист должен быть один");
        }
        Long artist = Long.parseLong(artistRaw[0]);

        String[] genreRaw = parameterMap.get(GENRE_PARAM_NAME);
        if(genreRaw == null){
            throw new IllegalArgumentException("Неотмеченные жанры");
        }
        Set<Long> genres = new HashSet<>();
        for (String s : genreRaw) {
            genres.add(Long.parseLong(s));
        }
        String[] abouts = parameterMap.get(ABOUT_PARAM_NAME);
        if(abouts == null || abouts[0].isEmpty() || abouts.length != 1){
            throw new IllegalArgumentException("Пустое поле \"О себе\"");
        }
        String about = abouts[0];

        VoteCreatorDTO voteCreatorDTO = new VoteCreatorDTO(artist, genres, about);
        voteService.save(voteCreatorDTO);

        resp.sendRedirect("/voteappV2-1.0-SNAPSHOT/vote");
    }
}
