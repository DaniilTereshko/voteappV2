package org.example.dao.repositories;

import org.example.dao.entities.Genre;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IGenreRepository extends ICRUDRepository<Genre, Long> {
    Set<Genre> findByIdIn(Collection<Long> id);
}
