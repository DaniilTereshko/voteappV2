package org.example.controllers.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.core.dto.ArtistDTO;
import org.example.services.api.IArtistService;
import org.example.services.factory.ArtistServiceFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {
    private IArtistService artistService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.artistService = ArtistServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<ArtistDTO> artistDTOS = artistService.get();
        artistDTOS.forEach(a -> {
            writer.write(a.getId() + " " + a.getName());
        });

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }
}
