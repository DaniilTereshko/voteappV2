package org.example.services.classes;

import org.example.core.dto.GenreCreateDTO;
import org.example.core.dto.GenreDTO;
import org.example.dao.api.IGenreHibernateDao;
import org.example.dao.classes.db.hibernate.entities.GenreEntity;
import org.example.services.api.IGenreService;

import java.util.ArrayList;
import java.util.List;

public class GenreService implements IGenreService {
    private final IGenreHibernateDao genreDao;

    public GenreService(IGenreHibernateDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<GenreDTO> get() {
        List<GenreEntity> genreEntities = genreDao.get();
        List<GenreDTO> genres = new ArrayList<>();
        for(GenreEntity g:genreEntities){
            genres.add(entityToDto(g));
        }
        return genres;
    }

    @Override
    public GenreDTO get(long id) {
        GenreEntity genreEntity = genreDao.get(id);
        return entityToDto(genreEntity);
    }

    @Override
    public GenreDTO save(GenreCreateDTO item) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setName(item.getName());
        GenreEntity genreEntity = dtoToEntity(genreDTO);
        GenreEntity savedEntity = genreDao.save(genreEntity);
        return entityToDto(savedEntity);
    }
    private GenreEntity dtoToEntity(GenreDTO genreDTO){
        return new GenreEntity(genreDTO.getId(), genreDTO.getName());
    }
    private GenreDTO entityToDto(GenreEntity genreEntity) {
        return new GenreDTO(genreEntity.getId(), genreEntity.getName());
    }
}
