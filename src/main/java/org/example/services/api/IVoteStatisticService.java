package org.example.services.api;

import org.example.core.dto.ArtistDTO;
import org.example.core.dto.GenreDTO;
import org.example.core.dto.StatisticDTO;
import org.example.core.dto.VoteDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IVoteStatisticService {
    StatisticDTO getTop();
    Map<GenreDTO, Long> getGenreTop();
    Map<ArtistDTO, Long> getArtistsTop();
    List<VoteDTO> getAboutTop();
}
