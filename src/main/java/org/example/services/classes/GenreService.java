package org.example.services.classes;

import org.example.core.dto.GenreCreateDTO;
import org.example.core.dto.GenreDTO;
import org.example.dao.repositories.IGenreRepository;
import org.example.dao.entities.Genre;
import org.example.services.api.IGenreService;

import java.util.ArrayList;
import java.util.List;

public class GenreService implements IGenreService {
    private final IGenreRepository genreDao;

    public GenreService(IGenreRepository genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<GenreDTO> get() {
        List<Genre> genreEntities = genreDao.findAll();
        List<GenreDTO> genres = new ArrayList<>();
        for(Genre g:genreEntities){
            genres.add(entityToDto(g));
        }
        return genres;
    }

    @Override
    public GenreDTO get(long id) {
        Genre genre = genreDao.findById(id).get();
        return entityToDto(genre);
    }

    @Override
    public GenreDTO save(GenreCreateDTO item) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setName(item.getName());
        Genre genre = dtoToEntity(genreDTO);
        Genre savedEntity = genreDao.save(genre);
        return entityToDto(savedEntity);
    }
    private Genre dtoToEntity(GenreDTO genreDTO){
        return new Genre(genreDTO.getId(), genreDTO.getName());
    }
    private GenreDTO entityToDto(Genre genre) {
        return new GenreDTO(genre.getId(), genre.getName());
    }
}
