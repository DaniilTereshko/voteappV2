package org.example.controllers.web.servlets;

import org.example.core.dto.ArtistDTO;
import org.example.core.dto.GenreDTO;
import org.example.core.dto.StatisticDTO;
import org.example.core.dto.VoteDTO;
import org.example.services.api.IVoteStatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class VoteResultsController {
    private final IVoteStatisticService voteStatisticService;
    public VoteResultsController(IVoteStatisticService voteStatisticService) {
        this.voteStatisticService = voteStatisticService;
    }
    @GetMapping(path = "/result")
    public String results(Model model) {
        StatisticDTO top = voteStatisticService.getTop();
        Map<ArtistDTO, Long> topArtist = top.getArtistsTop();
        model.addAttribute("artists", topArtist);
        Map<GenreDTO, Long> topGenres = top.getGenreTop();
        model.addAttribute("genres", topGenres);
        List<VoteDTO> topAbouts = top.getAboutTop();
        model.addAttribute("abouts", topAbouts);
        return "result";
    }
}
