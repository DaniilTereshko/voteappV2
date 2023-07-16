package org.example.core.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class VoteDTO implements IDTO{
    private long id;
    private Long artistId;
    private Set<Long> genreId;
    private String about;
    private LocalDateTime date;

    public VoteDTO() {
    }

    public VoteDTO(long id, Long artistId, Set<Long> genreId, String about, LocalDateTime date) {
        this.id = id;
        this.artistId = artistId;
        this.genreId = genreId;
        this.about = about;
        this.date = date;
    }

    public VoteDTO(Long artistId, Set<Long> genreId, String about, LocalDateTime date) {
        this.artistId = artistId;
        this.genreId = genreId;
        this.about = about;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public Long getArtistId() {
        return artistId;
    }


    public Set<Long> getGenreId() {
        return genreId;
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

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public void setGenreId(Set<Long> genreId) {
        this.genreId = genreId;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
