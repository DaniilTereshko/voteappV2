package org.example.controllers.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.core.dto.ArtistDTO;
import org.example.core.dto.GenreDTO;
import org.example.core.dto.StatisticDTO;
import org.example.core.dto.VoteDTO;
import org.example.services.api.IArtistService;
import org.example.services.api.IGenreService;
import org.example.services.api.IVoteStatisticService;
import org.example.services.factory.ArtistServiceFactory;
import org.example.services.factory.GenreServiceFactory;
import org.example.services.factory.VoteStatisticServiceFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name ="VoteResultsServlet", urlPatterns = "/result")
public class VoteResultsServlet extends HttpServlet {
    private IArtistService artistService;
    private IGenreService genreService;
    private IVoteStatisticService voteStatisticService;

    public void init() throws ServletException {
        super.init();
        this.artistService = ArtistServiceFactory.getInstance();
        this.genreService = GenreServiceFactory.getInstance();
        this.voteStatisticService = VoteStatisticServiceFactory.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatisticDTO top = voteStatisticService.getTop();
        Map<ArtistDTO, Long> topArtist = top.getArtistsTop();
        req.setAttribute("artists", topArtist);
        Map<GenreDTO, Long> topGenres = top.getGenreTop();
        req.setAttribute("genres", topGenres);
        List<VoteDTO> topAbouts = top.getAboutTop();
        req.setAttribute("abouts", topAbouts);
        req.getRequestDispatcher("/WEB-INF/result.jspx").forward(req,resp);
    }
}
