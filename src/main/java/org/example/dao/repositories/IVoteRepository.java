package org.example.dao.repositories;

import org.example.dao.entities.Vote;
import org.springframework.data.jpa.repository.EntityGraph;

public interface IVoteRepository extends ICRUDRepository<Vote, Long> {
    @EntityGraph(value = "withGenresAndArtist", type = EntityGraph.EntityGraphType.LOAD)
    @Override
    <S extends Vote> S save(S entity);
}
