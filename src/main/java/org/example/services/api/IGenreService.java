package org.example.services.api;

import org.example.core.dto.GenreCreateDTO;
import org.example.core.dto.GenreDTO;
import org.example.dao.entities.Genre;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IGenreService extends ICRUDService<GenreDTO, GenreCreateDTO>{
    Set<Genre> findByIdIn(Collection<Long> id);

}
