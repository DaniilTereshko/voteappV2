package org.example.core.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class StatisticDTO {
    private Map<ArtistDTO, Long> artistsTop;
    private Map<GenreDTO, Long> genreTop;
    private List<VoteDTO> aboutTop;

    public Map<ArtistDTO, Long> getArtistsTop() {
        return artistsTop;
    }

    public void setArtistsTop(Map<ArtistDTO, Long> artistsTop) {
        this.artistsTop = artistsTop;
    }

    public Map<GenreDTO, Long> getGenreTop() {
        return genreTop;
    }

    public void setGenreTop(Map<GenreDTO, Long> genreTop) {
        this.genreTop = genreTop;
    }

    public List<VoteDTO> getAboutTop() {
        return aboutTop;
    }

    public void setAboutTop(List<VoteDTO> aboutTop) {
        this.aboutTop = aboutTop;
    }
}
