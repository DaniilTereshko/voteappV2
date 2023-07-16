package org.example.controllers.web.servlets;

import org.example.core.dto.GenreDTO;
import org.example.core.mappers.VoteMapperUtil;
import org.example.services.api.IGenreService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {
    private final IGenreService genreService;
    private final ModelMapper modelMapper;
    public GenreController(IGenreService genreService, ModelMapper modelMapper) {
        this.genreService = genreService;
        this.modelMapper = modelMapper;
    }
    @GetMapping(path = "/genres")
    public List<GenreDTO> get(){
        return null; //TODO !!!
    }
}
