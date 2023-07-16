package org.example.services.classes;

import org.example.core.dto.GenreCreateDTO;
import org.example.core.mappers.VoteMapperUtil;
import org.example.dao.repositories.IGenreRepository;
import org.example.dao.entities.Genre;
import org.example.services.api.IGenreService;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class GenreService implements IGenreService {
    private final IGenreRepository genreDao;
    private final ModelMapper modelMapper;

    public GenreService(IGenreRepository genreDao, ModelMapper modelMapper) {
        this.genreDao = genreDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Genre> get() {
        return genreDao.findAll();
    }

    @Override
    public Genre get(long id) {
        return genreDao.findById(id).get();
    }

    @Override
    public Genre save(GenreCreateDTO item) {
        return genreDao.save(modelMapper.map(item, Genre.class));
    }
    @Override
    public Set<Genre> findByIdIn(Collection<Long> id) {
        return genreDao.findByIdIn(id);
    }

}
