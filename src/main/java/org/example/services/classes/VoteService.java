package org.example.services.classes;

import org.example.core.dto.VoteCreatorDTO;
import org.example.core.dto.VoteDTO;
import org.example.core.mappers.VoteMapperUtil;
import org.example.dao.entities.Vote;
import org.example.dao.repositories.IVoteRepository;
import org.example.services.api.IArtistService;
import org.example.services.api.IGenreService;
import org.example.services.api.IVoteService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class VoteService implements IVoteService {
    private final IVoteRepository voteDao;
    private final IArtistService artistService;
    private final IGenreService genreService;
    private final VoteMapperUtil modelMapper;

    public VoteService(IVoteRepository voteDao, IArtistService artistService, IGenreService genreService, VoteMapperUtil modelMapper) {
        this.voteDao = voteDao;
        this.artistService = artistService;
        this.genreService = genreService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Vote> get() {
        return voteDao.findAll();
    }

    @Override
    public Vote get(long id) {
        return voteDao.findById(id).get();
    }

    @Override
    public Vote save(VoteCreatorDTO item) {
        validate(item);
        VoteDTO voteDTO = new VoteDTO(item.getArtist(), item.getGenres(), item.getAbout(), LocalDateTime.now());
        Vote vote = modelMapper.toEntity(voteDTO);
        voteDao.save(vote);
        return vote;
    }
    private void validate(VoteCreatorDTO voteCreatorDTO){
        Set<Long> genres = voteCreatorDTO.getGenres();
        if(genres == null || genres.size() < 3 || genres.size() > 5){
            throw new IllegalArgumentException("Количество выбранных жанров должно быть от 3 до 5");
        }
    }
}
