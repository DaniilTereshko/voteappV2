package org.example.controllers.web.servlets;

import org.example.core.dto.ArtistDTO;
import org.example.services.api.IArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArtistController {
    private final IArtistService artistService;
    private final ModelMapper modelMapper;
    public ArtistController(IArtistService artistService, ModelMapper modelMapper) {
        this.artistService = artistService;
        this.modelMapper = modelMapper;
    }
    @GetMapping(path = "/artists")
    public List<ArtistDTO> get(){
        return null;//TODO !!!;
    }
}
