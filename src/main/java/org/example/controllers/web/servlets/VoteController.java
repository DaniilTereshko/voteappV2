package org.example.controllers.web.servlets;

import org.example.core.dto.ArtistDTO;
import org.example.core.dto.GenreDTO;
import org.example.core.dto.VoteCreatorDTO;
import org.example.services.api.IArtistService;
import org.example.services.api.IGenreService;
import org.example.services.api.IVoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class VoteController {
    private final IArtistService artistService;
    private final IGenreService genreService;
    private final IVoteService voteService;

    public VoteController(IArtistService artistService, IGenreService genreService, IVoteService voteService) {
        this.artistService = artistService;
        this.genreService = genreService;
        this.voteService = voteService;
    }

    @GetMapping(path = "/vote")
    public String get(Model model){
        List<ArtistDTO> artists = artistService.get();
        List<GenreDTO> genres = genreService.get();
        model.addAttribute("artists", artists);
        model.addAttribute("genres", genres);
        return "vote";
    }
    @PostMapping(path = "/vote")
    public String post(
            @RequestParam Long[] artist,
            @RequestParam Set<Long> genre,
            @RequestParam String about,
            Model model){
        if(artist.length != 1){
            throw new IllegalArgumentException("Артист должен быть один");
        }
        if(genre == null){
            throw new IllegalArgumentException("Неотмеченные жанры");
        }
        if(about == null || about.isEmpty()){
            throw new IllegalArgumentException("Пустое поле \"О себе\"");
        }
        VoteCreatorDTO voteDTO = new VoteCreatorDTO(artist[0], genre, about);
        voteService.save(voteDTO);
        return "redirect:/app/vote";
    }
}
