package org.example.core.dto;

import java.util.Set;

public class VoteCreatorDTO {
    private final Long artist;
    private final Set<Long> genres;
    private final String about;

    public VoteCreatorDTO(Long artist, Set<Long> genres, String about) {
        this.artist = artist;
        this.genres = genres;
        this.about = about;
    }

    public Long getArtist() {
        return artist;
    }


    public Set<Long> getGenres() {
        return genres;
    }


    public String getAbout() {
        return about;
    }

}
