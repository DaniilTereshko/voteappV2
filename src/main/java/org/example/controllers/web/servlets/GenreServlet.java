package org.example.controllers.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.core.dto.GenreCreateDTO;
import org.example.core.dto.GenreDTO;
import org.example.services.api.IGenreService;
import org.example.services.factory.GenreServiceFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/genre")
public class GenreServlet extends HttpServlet {
    private IGenreService genreService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        super.init();
        this.genreService = GenreServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        List<GenreDTO> genreDTOS = genreService.get();
        writer.write(objectMapper.writeValueAsString(genreDTOS));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8;");
        req.setCharacterEncoding("UTF-8");

        GenreCreateDTO dto = this.objectMapper.readValue(req.getInputStream(), GenreCreateDTO.class);
        genreService.save(dto);
    }
}
