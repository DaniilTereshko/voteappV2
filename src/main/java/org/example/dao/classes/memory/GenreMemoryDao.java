package org.example.dao.classes.memory;

import org.example.core.dto.GenreDTO;
import org.example.dao.api.IGenreDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreMemoryDao implements IGenreDao {
    private final Map<Long, GenreDTO> genres = new HashMap<>();


    public GenreMemoryDao() {
        {
            GenreDTO dto = new GenreDTO(1L, "Hip-hop");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(2L, "Диско");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(3L, "Реп");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(4L, "Эстрада");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(5L, "Классика");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(6L, "Электроника");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(7L, "Рок");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(8L, "Кантри");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(9L, "Джаз");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(10L, "Фольк");
            genres.put(dto.getId(), dto);
        }
    }

    @Override
    public List<GenreDTO> get() {
        return new ArrayList<>(this.genres.values());
    }

    @Override
    public GenreDTO get(long id) {
        return this.genres.get(id);
    }

    @Override
    public GenreDTO save(GenreDTO item) {
        this.genres.put(item.getId(), item);
        return item;
    }
}

