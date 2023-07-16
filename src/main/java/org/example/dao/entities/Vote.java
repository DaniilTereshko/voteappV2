package org.example.dao.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@NamedEntityGraph(
        name = "withGenresAndArtist",
        attributeNodes = {
                @NamedAttributeNode("genres"),
                @NamedAttributeNode("artist")
        }
)
@Entity
@Table(name = "votes")
public class Vote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "votes_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "votes_seq", sequenceName = "votes_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "about")
    private String about;
    @Column(name = "date")
    private LocalDateTime date;
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "votes_genres",
            joinColumns = @JoinColumn(name = "id_vote"),
            inverseJoinColumns = @JoinColumn(name = "id_genre"))
    private Set<Genre> genres = new HashSet<>();
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "votes_artists",
            joinColumns = @JoinColumn(name = "id_vote"),
            inverseJoinColumns = @JoinColumn(name = "id_artist"))
    private Artist artist;

    public Vote() {//TODO из dto в entity на сервисе
    }

    public Vote(Long id, String about, LocalDateTime date) {
        this.id = id;
        this.about = about;
        this.date = date;
    }

    public Vote(Long id, String about, LocalDateTime date, Set<Genre> genres, Artist artist) {
        this.id = id;
        this.about = about;
        this.date = date;
        this.genres = genres;
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote that = (Vote) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getAbout() != null ? !getAbout().equals(that.getAbout()) : that.getAbout() != null) return false;
        return getDate() != null ? getDate().equals(that.getDate()) : that.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getAbout() != null ? getAbout().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }
}
