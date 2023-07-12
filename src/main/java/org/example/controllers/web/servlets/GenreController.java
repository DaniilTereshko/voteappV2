package org.example.controllers.web.servlets;

import org.example.core.dto.GenreDTO;
import org.example.services.api.IGenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {
    private final IGenreService genreService;
    public GenreController(IGenreService genreService) {
        this.genreService = genreService;
    }
    @GetMapping(path = "/genres")
    public List<GenreDTO> get(){
        return genreService.get();
    }
}
