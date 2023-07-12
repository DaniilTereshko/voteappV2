package org.example.controllers.web.servlets;

import jakarta.servlet.http.HttpServlet;
import org.example.core.dto.ArtistDTO;
import org.example.services.api.IArtistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArtistController extends HttpServlet {
    private final IArtistService artistService;
    public ArtistController(IArtistService artistService) {
        this.artistService = artistService;
    }
    @GetMapping(path = "/artists")
    public List<ArtistDTO> get(){
        return artistService.get();
    }
}
