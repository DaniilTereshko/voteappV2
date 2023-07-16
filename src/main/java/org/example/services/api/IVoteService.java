package org.example.services.api;

import org.example.core.dto.VoteCreatorDTO;
import org.example.core.dto.VoteDTO;
import org.example.dao.entities.Vote;

public interface IVoteService extends ICRUDService<Vote, VoteCreatorDTO>{
}
