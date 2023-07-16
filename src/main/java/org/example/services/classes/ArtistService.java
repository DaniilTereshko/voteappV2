package org.example.services.classes;

import org.example.core.dto.ArtistCreateDTO;
import org.example.core.mappers.VoteMapperUtil;
import org.example.dao.entities.Artist;
import org.example.dao.repositories.IArtistRepository;
import org.example.services.api.IArtistService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ArtistService implements IArtistService {
    private final IArtistRepository artistDao;
    private final ModelMapper modelMapper;

    public ArtistService(IArtistRepository artistDao, ModelMapper modelMapper) {
        this.artistDao = artistDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Artist> get() {
        return artistDao.findAll();
    }

    @Override
    public Artist get(long id) {
        return artistDao.findById(id).get();
    }

    @Override
    public Artist save(ArtistCreateDTO item) {
        return artistDao.save(modelMapper.map(item, Artist.class));
    }
}
