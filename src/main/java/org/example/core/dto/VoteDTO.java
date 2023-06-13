package org.example.core.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class VoteDTO {
    private long id;
    private Long artist;
    private Set<Long> genres;
    private String about;
    private LocalDateTime date;

    public VoteDTO() {
    }

    public VoteDTO(long id, Long artist, Set<Long> genres, String about, LocalDateTime date) {
        this.id = id;
        this.artist = artist;
        this.genres = genres;
        this.about = about;
        this.date = date;
    }

    public VoteDTO(Long artist, Set<Long> genres, String about, LocalDateTime date) {
        this.artist = artist;
        this.genres = genres;
        this.about = about;
        this.date = date;
    }

    public long getId() {
        return id;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setArtist(Long artist) {
        this.artist = artist;
    }

    public void setGenres(Set<Long> genres) {
        this.genres = genres;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
