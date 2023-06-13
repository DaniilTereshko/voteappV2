package org.example.dao.classes.memory;

import org.example.core.dto.ArtistDTO;
import org.example.dao.api.IArtistDao;

import java.util.ArrayList;
import java.util.List;

public class ArtistMemoryDao implements IArtistDao {
    private final List<ArtistDTO> artists = new ArrayList<>();

    public ArtistMemoryDao() {
        artists.add(new ArtistDTO(1L, "Морген"));
        artists.add(new ArtistDTO(2L, "Пугачёва"));
        artists.add(new ArtistDTO(3L, "Моцарт"));
        artists.add(new ArtistDTO(4L, "50cent"));
    }

    @Override
    public List<ArtistDTO> get() {
        return new ArrayList<>(this.artists);
    }

    @Override
    public ArtistDTO get(long id) {
        return this.artists.stream().filter(a -> a.getId() == id).findFirst().orElseThrow();
    }

    @Override
    public ArtistDTO save(ArtistDTO item) {
        this.artists.add(item);
        return item;
    }
}
