package org.example.dao.classes.db.hibernate.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "votes")
public class VoteEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "artists_id_seq", sequenceName = "artists_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "artists_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "about")
    private String text;
    @Column(name = "date")
    private LocalDateTime date;
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "votes_genres",
            joinColumns = @JoinColumn(name = "id_vote"),
            inverseJoinColumns = @JoinColumn(name = "id_genre"))
    private Set<GenreEntity> genres = new HashSet<>();
    @OneToOne
    @JoinTable(name = "votes_artists",
            joinColumns = @JoinColumn(name = "id_vote"),
            inverseJoinColumns = @JoinColumn(name = "id_artist"))
    private ArtistEntity artist;

    public VoteEntity() {//TODO из dto в entity на сервисе
    }

    public VoteEntity(Long id, String text, LocalDateTime date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public VoteEntity(Long id, String text, LocalDateTime date, Set<GenreEntity> genres, ArtistEntity artist) {
        this.id = id;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Set<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreEntity> genres) {
        this.genres = genres;
    }

    public ArtistEntity getArtist() {
        return artist;
    }

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoteEntity that = (VoteEntity) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getText() != null ? !getText().equals(that.getText()) : that.getText() != null) return false;
        return getDate() != null ? getDate().equals(that.getDate()) : that.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }
}
