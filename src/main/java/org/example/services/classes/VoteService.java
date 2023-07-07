package org.example.services.classes;

import org.example.core.dto.ArtistDTO;
import org.example.core.dto.GenreDTO;
import org.example.core.dto.VoteCreatorDTO;
import org.example.core.dto.VoteDTO;
import org.example.dao.repositories.IVoteRepository;
import org.example.dao.entities.Artist;
import org.example.dao.entities.Genre;
import org.example.dao.entities.Vote;
import org.example.services.api.IArtistService;
import org.example.services.api.IGenreService;
import org.example.services.api.IVoteService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VoteService implements IVoteService {
    private final IVoteRepository voteDao;
    private final IArtistService artistService;
    private final IGenreService genreService;

    public VoteService(IVoteRepository voteDao, IArtistService artistService, IGenreService genreService) {
        this.voteDao = voteDao;
        this.artistService = artistService;
        this.genreService = genreService;
    }

    @Override
    public List<VoteDTO> get() {
        List<Vote> voteEntities = voteDao.findAll();
        List<VoteDTO> votes = new ArrayList<>();
        for(Vote v:voteEntities){
            votes.add(entityToDto(v));
        }
        return votes;
    }

    @Override
    public VoteDTO get(long id) {
        Vote v = voteDao.findById(id).get();
        return entityToDto(v);
    }

    @Override
    public VoteDTO save(VoteCreatorDTO item) {
        validate(item);
        VoteDTO voteDTO = new VoteDTO(item.getArtist(), item.getGenres(), item.getAbout(), LocalDateTime.now());
        Vote vote = dtoToEntity(voteDTO);
        voteDao.save(vote);
        return entityToDto(vote);
    }
    private void validate(VoteCreatorDTO voteCreatorDTO){
        Set<Long> genres = voteCreatorDTO.getGenres();
        if(genres == null || genres.size() < 3 || genres.size() > 5){
            throw new IllegalArgumentException("Количество выбранных жанров должно быть от 3 до 5");
        }
    }
    private Vote dtoToEntity(VoteDTO voteDTO){
        ArtistDTO artistDTO = artistService.get(voteDTO.getArtist());
        Artist artist = new Artist(artistDTO.getId(), artistDTO.getName());
        Set<Genre> genreEntities = new HashSet<>();
        for(Long i:voteDTO.getGenres()){
            GenreDTO genreDTO = genreService.get(i);
            Genre genre = new Genre(genreDTO.getId(),genreDTO.getName());
            genreEntities.add(genre);
        }
        return new Vote(voteDTO.getId(), voteDTO.getAbout(), voteDTO.getDate(), genreEntities, artist);
    }
    private VoteDTO entityToDto(Vote vote) {
        Set<Long> genres = new HashSet<>();
        for(Genre g: vote.getGenres()){
            genres.add(g.getId());
        }
        return new VoteDTO(vote.getId(), vote.getArtist().getId(), genres, vote.getText(), vote.getDate());
    }
}
