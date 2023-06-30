package org.example.dao.classes.memory;

import org.example.core.dto.VoteDTO;
import org.example.dao.api.ICRUDDao;

import java.util.ArrayList;
import java.util.List;

public class VoteMemoryDao implements ICRUDDao<VoteDTO> {
    private final List<VoteDTO> votes = new ArrayList<>();
    @Override
    public List<VoteDTO> get() {
        return new ArrayList<>(votes);
    }

    @Override
    public VoteDTO get(long id) {
        return this.votes.stream().filter(v -> v.getId() == id).findFirst().orElseThrow();
    }

    @Override
    public VoteDTO save(VoteDTO item) {
        this.votes.add(item);
        return item;
    }
}
