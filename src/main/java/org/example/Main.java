package org.example;

import org.example.controllers.factory.ApplicationContextBean;
import org.example.controllers.factory.ApplicationContextFactory;
import org.example.core.dto.VoteCreatorDTO;
import org.example.services.api.IVoteService;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ApplicationContextBean instance = ApplicationContextFactory.getInstance();
        IVoteService voteService = instance.getVoteService();
        VoteCreatorDTO voteCreatorDTO = new VoteCreatorDTO(2L, Set.of(1L,2L,3L), "afasf");
        voteService.save(voteCreatorDTO);
    }
}
