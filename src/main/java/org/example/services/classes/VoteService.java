package org.example.services.classes;

import org.example.core.dto.ArtistDTO;
import org.example.core.dto.GenreDTO;
import org.example.core.dto.VoteCreatorDTO;
import org.example.core.dto.VoteDTO;
import org.example.dao.api.IVoteHibernateDao;
import org.example.dao.classes.db.hibernate.entities.ArtistEntity;
import org.example.dao.classes.db.hibernate.entities.GenreEntity;
import org.example.dao.classes.db.hibernate.entities.VoteEntity;
import org.example.services.api.IArtistService;
import org.example.services.api.IGenreService;
import org.example.services.api.IVoteService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VoteService implements IVoteService {
    private final IVoteHibernateDao voteDao;
    private final IArtistService artistService;
    private final IGenreService genreService;

    public VoteService(IVoteHibernateDao voteDao, IArtistService artistService, IGenreService genreService) {
        this.voteDao = voteDao;
        this.artistService = artistService;
        this.genreService = genreService;
    }

    @Override
    public List<VoteDTO> get() {
        List<VoteEntity> voteEntities = voteDao.get();
        List<VoteDTO> votes = new ArrayList<>();
        for(VoteEntity v:voteEntities){
            votes.add(entityToDto(v));
        }
        return votes;
    }

    @Override
    public VoteDTO get(long id) {
        VoteEntity v = voteDao.get(id);
        return entityToDto(v);
    }

    @Override
    public VoteDTO save(VoteCreatorDTO item) {
        validate(item);
        VoteDTO voteDTO = new VoteDTO(item.getArtist(), item.getGenres(), item.getAbout(), LocalDateTime.now());
        VoteEntity voteEntity = dtoToEntity(voteDTO);
        voteDao.save(voteEntity);
        return entityToDto(voteEntity);
    }
    private void validate(VoteCreatorDTO voteCreatorDTO){
        Set<Long> genres = voteCreatorDTO.getGenres();
        if(genres == null || genres.size() < 3 || genres.size() > 5){
            throw new IllegalArgumentException("Количество выбранных жанров должно быть от 3 до 5");
        }
    }
    private VoteEntity dtoToEntity(VoteDTO voteDTO){
        ArtistDTO artistDTO = artistService.get(voteDTO.getArtist());
        ArtistEntity artistEntity = new ArtistEntity(artistDTO.getId(), artistDTO.getName());
        Set<GenreEntity> genreEntities = new HashSet<>();
        for(Long i:voteDTO.getGenres()){
            GenreDTO genreDTO = genreService.get(i);
            GenreEntity genreEntity = new GenreEntity(genreDTO.getId(),genreDTO.getName());
            genreEntities.add(genreEntity);
        }
        return new VoteEntity(voteDTO.getId(), voteDTO.getAbout(), voteDTO.getDate(), genreEntities, artistEntity);
    }
    private VoteDTO entityToDto(VoteEntity voteEntity) {
        Set<Long> genres = new HashSet<>();
        for(GenreEntity g: voteEntity.getGenres()){
            genres.add(g.getId());
        }
        return new VoteDTO(voteEntity.getId(), voteEntity.getArtist().getId(), genres, voteEntity.getText(), voteEntity.getDate());
    }
}
